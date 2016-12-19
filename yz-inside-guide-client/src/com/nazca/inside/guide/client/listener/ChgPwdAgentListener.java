/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazca.inside.guide.client.listener;

/**
 *
 * @author blader
 */
public interface ChgPwdAgentListener {

    void onStartChgPwd(String userid);

    void onPwdChgSuc(String userid);

    void onPwdChgFailed(String userid, String msg, int code);
}
