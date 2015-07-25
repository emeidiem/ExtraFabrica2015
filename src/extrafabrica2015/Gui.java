package extrafabrica2015;

import controlP5.ControlP5;

public class Gui {

	ExtraFabrica2015 p5;
	ControlP5 cp5;

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
		cp5.addSlider("GRAVITY").setPosition(100, 50).setRange(-100, 100);
	}
}
