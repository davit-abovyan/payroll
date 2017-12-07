package am.vector.dao.impl;

import am.vector.BaseIntegrationTest;
import am.vector.constants.LeaveType;
import am.vector.model.AbsenceModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class AbsenceDAOTest extends BaseIntegrationTest {

    private AbsenceModel absence;

    @Autowired
    private AbsenceDAO absenceDAO;

    @Before
    public void setup(){
        absence = new AbsenceModel(LeaveType.MTL, 1000, LocalDate.now(),LocalDate.now().plusDays(3));
    }

    @After
    public void tearDown(){

    }

    @Test
    public void createAbsens_Success() throws Exception{
        absenceDAO.create(absence);
    }
}
