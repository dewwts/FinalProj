package Pets;

import javafx.scene.image.Image;

public class Cat extends BasePets {

    public Cat(String name) {
        super(name, 80, 60, new Image("cat.png")); // ส่งภาพ cat.png
    }

    @Override
    public void specialAbility() {
        System.out.println(getName() + " ใช้ความสามารถ: การลอบโจมตีเงียบ!");
    }
}

