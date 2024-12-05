package Pets;

import javafx.scene.image.Image;

public class Unicorn extends BasePets {

    public Unicorn(String name) {
        super(name, 150, 100, new Image("unicorn.png")); // ส่งภาพ unicorn.png
    }

    @Override
    public void specialAbility() {
        System.out.println(getName() + " ใช้ความสามารถ: การรักษาและเพิ่มพลัง!");
    }
}


