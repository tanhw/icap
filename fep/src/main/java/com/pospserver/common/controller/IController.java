/**
 * 
 */
package com.pospserver.common.controller;

import com.pospserver.models.MessageMina;

/**
 * @author charely
 * 
 */
public interface IController {


	MessageMina processmessage(MessageMina inmessage) throws Exception;

}
