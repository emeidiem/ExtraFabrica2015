package extrafabrica2015;

import processing.core.PImage;
import controlP5.Button;
import controlP5.ControlP5;

public class Gui {

	ExtraFabrica2015 p5;
	ControlP5 cp5;
	PImage gravityOnImg1, gravityOnImg2, gravityOnImg3;
	Button gravityOn;

	Gui(ExtraFabrica2015 _p5) { // CONSTRUCTOR
		p5 = _p5;
		cp5 = new ControlP5(p5);
		createSliders();
		cp5.setAutoDraw(false);

	}

	void run() {
		p5.hint(p5.DISABLE_DEPTH_TEST);
		p5.cam.beginHUD();
		cp5.draw();
		p5.cam.endHUD();
		p5.hint(p5.ENABLE_DEPTH_TEST);
	}

	void createSliders() {
		gravityOnImg1 = p5.loadImage("SoftModelling_Icon_Gravity_A.png");
		gravityOnImg2 = p5.loadImage("SoftModelling_Icon_Gravity_B.png");
		gravityOnImg3 = p5.loadImage("SoftModelling_Icon_Gravity_C.png");
		cp5.addSlider("GRAVITY").setPosition(100, 50).setRange(-100, 100);
		cp5.addSlider("WIND").setPosition(100, 75).setRange(-100, 100);
		gravityOn = cp5.addButton("GRAVITYON")
				.setPosition(100, 100)
				.setImages(gravityOnImg1,gravityOnImg2,gravityOnImg3)
				.updateSize();
	}
}
