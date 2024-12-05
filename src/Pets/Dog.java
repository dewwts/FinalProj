package Pets;

import javafx.scene.image.Image;

public class Dog extends BasePets {

    public Dog(String name) {
        super(name, 100, 50, new Image("dog.png")); // ส่งภาพ dog.png
    }

    @Override
    public void specialAbility() {
        System.out.println(getName() + " ใช้ความสามารถ: การเห่าเสียงดังเพื่อขับไล่ศัตรู!");
    }
}

