package cnam.glg204.quiz.server.service.statistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import cnam.glg204.quiz.common.config.Constantes;
import cnam.glg204.quiz.common.dto.ResultDto;
import cnam.glg204.quiz.common.dto.ResultWithUserDto;
import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.enums.SessionStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoRunningSessionQuizForThisCoursException;
import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.cours.CoursDao;
import cnam.glg204.quiz.server.domain.result.Result;
import cnam.glg204.quiz.server.domain.result.ResultDao;
import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuiz;
import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuizDao;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;

@Service("statisticService")
@Transactional
@Rollback(true)
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    ResultDao resultDao;

    @Autowired
    SessionQuizDao sessionQuizDao;

    @Autowired
    CoursDao coursDao;

    @Autowired
    UserDao userDao;

    @Override
    public void saveResult(ResultDto resultDto) throws NoRunningSessionQuizForThisCoursException, CheckException {
        Cours cours = coursDao.find(resultDto.getCoursId());
        if (cours == null) {
            throw new NoRunningSessionQuizForThisCoursException();
        }
        SessionQuiz session = sessionQuizDao.findRunningByCours(cours);
        if (session == null || session.getStatus() == SessionStatus.NOT_RUNNING) {
            throw new NoRunningSessionQuizForThisCoursException();
        } else {
            resultDto.setSessionQuizId(session.getId());
        }
        Result result = this.resultDtoToResult(resultDto);
        result.setDate(new java.util.Date());
        result.setObtainedPoints(resultDto.getPoints());
        Map<String, boolean[]> results = result.getAnswers();
        for (Map.Entry<String, boolean[]> e : results.entrySet()) {
            boolean[] r = e.getValue();
            if (r[0] != r[1]) {
                result.setObtainedPoints(0);
            }
        }
        result.checkData();
        resultDao.save(result);
        resultDto.setId(result.getId());
    }

    @Override
    public List<ResultWithUserDto> findResultsByUserQuestionAndSession(long userId, long questionId,
            long sessionId) {
        List<Result> listResult = resultDao.findResultsByUserQuestionAndSession(userId, questionId, sessionId);
        return this.listOfresultToListOfResultDto(listResult);

    }

    @Override
    public List<ResultWithUserDto> findResultsByUserAndSession(long userId, long sessionId) {
        List<Result> listResult = resultDao.findResultsByUserAndSession(userId, sessionId);
        List<ResultWithUserDto> results = this.listOfresultToListOfResultDto(listResult);
        return groupList(results);
    }

    @Override
    public List<ResultWithUserDto> findResultsByUserAndCours(long userId, long coursId) {
        Cours cours = coursDao.find(coursId);
        List<SessionQuiz> sessions = sessionQuizDao.findByCours(cours);
        List<ResultWithUserDto> results = new ArrayList<ResultWithUserDto>();
        for (SessionQuiz sessionQuiz : sessions) {
            results.addAll(findResultsByUserAndSession(userId, sessionQuiz.getId()));
        }
        return groupList(results);
    }

    @Override
    public List<ResultWithUserDto> findResultsByQuestionAndSession(long questionId, long sessionId) {
        List<Result> listResult = resultDao.findResultsByQuestionAndSession(questionId, sessionId);
        List<ResultWithUserDto> listDto = this.getJustLastestAnswers(this.listOfresultToListOfResultDto(listResult));
        return listDto;
    }

    @Override
    public List<ResultWithUserDto> findResultsBySession(long sessionId) {
        List<Result> listResult = resultDao.findResultsBySession(sessionId);
        List<ResultWithUserDto> results = this.listOfresultToListOfResultDto(listResult);
        return groupList(results);
    }

    @Override
    public List<ResultWithUserDto> findResultsByCours(long coursId) {
        Cours cours = coursDao.find(coursId);
        List<SessionQuiz> sessions = sessionQuizDao.findByCours(cours);
        List<ResultWithUserDto> results = new ArrayList<ResultWithUserDto>();
        for (SessionQuiz sessionQuiz : sessions) {
            results.addAll(findResultsBySession(sessionQuiz.getId()));
        }
        return groupList(results);
    }

    public List<ResultWithUserDto> listOfresultToListOfResultDto(List<Result> listResult) {
        ArrayList<ResultWithUserDto> listResultDto = new ArrayList<ResultWithUserDto>();
        for (Result result : listResult) {
            listResultDto.add(resultToResultDto(result));
        }
        return listResultDto;
    }

    public List<Result> listOfresultDtoToListOfResult(List<ResultDto> listResultDto) {
        ArrayList<Result> listResult = new ArrayList<Result>();
        for (ResultDto result : listResultDto) {
            listResult.add(resultDtoToResult(result));
        }
        return listResult;
    }

    public Result resultDtoToResult(ResultDto resultDto) {
        Result result = new Result();
        result.setId(resultDto.getId());
        User user = userDao.find(resultDto.getUserId());
        result.setUser(user);
        result.setAnswerTime(resultDto.getAnswerTime());
        result.setQuestionId(resultDto.getQuestionId());
        String q = resultDto.getQuestion();

        if (q != null) {
            q = (q.length() > 255) ? q.substring(0, 255) : q;
        }
        result.setQuestion(q);
        result.setAnswers(resultDto.getAnswers());
        SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DATE_FORMAT);
        if (resultDto.getDate() != null) {
            try {
                result.setDate(formatter.parse(resultDto.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        SessionQuiz sessionQuiz = sessionQuizDao.find(resultDto.getSessionQuizId());
        result.setSessionQuiz(sessionQuiz);
        result.setTitle(resultDto.getTitle());
        result.setObtainedPoints(resultDto.getObtainedPoints());
        result.setPoints(resultDto.getPoints());
        return result;
    }

    public ResultWithUserDto resultToResultDto(Result result) {
        ResultWithUserDto resultDto = new ResultWithUserDto();
        resultDto.setId(result.getId());
        resultDto.setUserDto(userToUserDto(result.getUser()));
        resultDto.setAnswerTime(result.getAnswerTime());
        resultDto.setQuestionId(result.getQuestionId());
        resultDto.setQuestion(result.getQuestion());
        resultDto.setAnswers(result.getAnswers());
        SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DATE_FORMAT);
        resultDto.setDate(formatter.format(result.getDate()));
        if (result.getSessionQuiz() != null) {
            resultDto.setSessionQuizId(result.getSessionQuiz().getId());
        }
        resultDto.setObtainedPoints(result.getObtainedPoints());
        resultDto.setTitle(result.getTitle());
        resultDto.setPoints(result.getPoints());
        return resultDto;
    }

    private UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAccountType(user.getAccountType());
        return userDto;
    }

    public List<ResultWithUserDto> groupList(List<ResultWithUserDto> list) {
        List<ResultWithUserDto> groupedList = new ArrayList<ResultWithUserDto>();
        for (ResultWithUserDto r1 : list) {
            boolean t = true;
            for (ResultWithUserDto r2 : groupedList) {
                if (r1.getUserDto().getId() == r2.getUserDto().getId()) {
                    r2.setPoints(r2.getPoints() + r1.getPoints());
                    r2.setObtainedPoints(r2.getObtainedPoints() + r1.getObtainedPoints());
                    r2.setAnswerTime(r2.getAnswerTime() + r1.getAnswerTime());
                    t = false;
                }

            }
            if (t) {
                groupedList.add(r1);
            }
        }
        return groupedList;
    }

    public List<ResultWithUserDto> getJustLastestAnswers(List<ResultWithUserDto> list) {
        List<ResultWithUserDto> groupedList = new ArrayList<ResultWithUserDto>();
        for (ResultWithUserDto r1 : list) {
            boolean t = true;
            for (int i = 0; i < groupedList.size(); i++) {
                ResultWithUserDto r2 = groupedList.get(i);
                if (r1.getUserDto().getId() == r2.getUserDto().getId()) {
                    if (r1.getDate() != null && r2.getDate() != null) {
                        SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DATE_FORMAT);
                        try {
                            Date d1 = formatter.parse(r1.getDate());
                            Date d2 = formatter.parse(r2.getDate());
                            if (d1.compareTo(d2) == 1) {
                                groupedList.remove(r2);
                                groupedList.add(r1);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    t = false;
                }
            }
            if (t) 
                groupedList.add(r1);
            
        }
        
        return groupedList;
    }
}
