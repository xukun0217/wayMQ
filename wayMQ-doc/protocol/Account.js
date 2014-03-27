
/*********************************************************************

 	Account.register()
 
	?class=Account
	&method=getInfo
	&this=[a_event_id]
	
 */

{
	"event":{
	},
	
	"need_auth":true, // or false
	"success":true, // or false 
}

/*********************************************************************
 * Account.login()
 */

?class=Event
&method=join
&this=[a_event_id]
&token=[a_token_id]
{
	"event":{
	},
	
	"need_auth":true, // or false
	"success":true, // or false 
}


/*********************************************************************

	Account.getInfo()

	?class=Account
	&method=getInfo
	&this=[a_account_id]
	&token=[a_token_id]

*/

{
	"event":{
	},
	
	"need_auth":true, // or false
	"success":true, // or false 
}


/*********************************************************************

	Account.logout()
	
	?class=Account
	&method=logout
	&this=[a_event_id]
	&token=[a_token_id]

 */

{
	"event":{
	},
	
	"need_auth":true, // or false
	"success":true, // or false 
}

