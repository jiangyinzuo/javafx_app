package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;

/**
 * @author Jiang Yinzuo
 */
public class RollCallRecordCmpController {

    @FXML
    private Text prescene;

    @FXML
    private Text rollCallType;

    @FXML
    private Text instant;

    @FXML
    private Text className;
    
    // ��ʼ������
    public void init(RollCall rollCall) {
    	this.prescene.setText(rollCall.getPresence());
    	this.rollCallType.setText(rollCall.getRollCallType());
    	this.instant.setText(rollCall.getRollCallTime().toString());
    }

}
