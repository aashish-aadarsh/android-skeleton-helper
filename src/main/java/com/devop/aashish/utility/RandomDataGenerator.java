package com.devop.aashish.utility;

import com.devop.aashish.model.UIComponent;

import java.util.LinkedHashSet;
import java.util.Set;

public class RandomDataGenerator {

    public static Set<UIComponent> getRandomData() {
        Set<UIComponent> random = new LinkedHashSet<>();
        random.add(new UIComponent("firstName", "EditText", "First Name", true));
        random.add(new UIComponent("lastName", "EditText", "Last Name", false));
        random.add(new UIComponent("gender", "Spinner", "Gender", false));
        random.add(new UIComponent("mobile", "EditText", "Mobile Number", true));
        random.add(new UIComponent("email", "EditText", "Email Id", true));
        return random;
    }
}
