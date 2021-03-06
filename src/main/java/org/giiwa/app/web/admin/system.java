/*
 * Copyright 2015 JIHU, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.giiwa.app.web.admin;

import org.giiwa.core.json.JSON;
import org.giiwa.core.task.Task;
import org.giiwa.framework.bean.User;
import org.giiwa.framework.web.*;

/**
 * web api: /admin/system <br>
 * used to control the "system"<br>
 * required "access.config.admin"
 * 
 * @author joe
 *
 */
public class system extends Model {

	/**
	 * Restart.
	 */
	@Path(path = "restart", login = true, access = "access.config.admin|access.config.restart.host", log = Model.METHOD_POST)
	public void restart() {

		JSON jo = new JSON();
		User me = User.dao.load(login.getId());
		String pwd = this.getString("pwd");

		if (me.validate(pwd)) {
			jo.put("state", "ok");

			new Task() {

				@Override
				public String getName() {
					return "restart";
				}

				@Override
				public void onExecute() {
					System.exit(0);
				}

				@Override
				public void onFinish() {

				}

			}.schedule(1000);
		} else {
			jo.put("state", "fail");
			jo.put("message", lang.get("invalid.password"));
		}

		this.response(jo);
	}

}
