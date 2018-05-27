/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import entity.User;
import java.util.HashMap;
import model.CompareOperator;
import model.Filter;
import model.ObjectModel;

/**
 *
 * @author dongvu
 */
public class AuthenticationValidate {

    private static HashMap<String, String> errorList;

    public static HashMap<String, String> validateLoginForm(String username, String password) {
        errorList = new HashMap<>();
        if (username == null || username.isEmpty()) {
            errorList.put("username", "Username can not null");
        }
        if (password == null || password.isEmpty()) {
            errorList.put("password", "Password can not null");
        }
        return errorList;
    }

    public static HashMap<String, String> vailidateRegisterForm(String username, String password, String repeatPassword) {
        errorList = new HashMap<>();
        ObjectModel<User> model = new ObjectModel<>(User.class);

        Filter filter = new Filter();
        Filter.Conditions cond = filter.new Conditions();
        cond.setCompare(CompareOperator.EQUAL);
        cond.setCompareValue(username);
        filter.addField("username", cond);
        if (model.checkObjectExist(new User(), filter)) {
            errorList.put("username", "username aready exist !!");
        } else if (!username.matches("^[a-z]+\\w{4}+.")) {
            errorList.put("username", "Must start with a character & greater than 6 !!");
        }
        if (password.isEmpty()) {
            errorList.put("password", "Password is required !!");
        }
        if (!password.equals(repeatPassword)) {
            errorList.put("rePassword", "Repeat password not match !!");
        }
        return errorList;
    }
}
