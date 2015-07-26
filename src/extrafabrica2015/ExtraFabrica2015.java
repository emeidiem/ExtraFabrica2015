package extrafabrica2015;

// version 2

import java.util.ArrayList;

import controlP5.ControlP5;
import peasy.PeasyCam;
import processing.core.PApplet;
import toxi.geom.Vec3D;
import toxi.physics.VerletPhysics;
import toxi.physics.VerletSpring;
import toxi.physics.behaviors.GravityBehavior;
import wblut.hemesh.HEC_FromFacelist;
import wblut.hemesh.HE_Selection;
import wblut.processing.WB_Render;

public class ExtraFabrica2015 extends PApplet {

	int numPart = 10;
	int spacing = 40;
	float totalLength = (numPart-1)*spacing;
	PeasyCam cam;
	VerletPhysics physics;
	Gui gui;
	float gravityValue = 9.8f;
	float windValue = 0f;
	boolean gravityOn = false;
	Mesh mesh;
	WB_Render render;

//	Surface surface;

	public void settings() {
		size(800, 600, P3D);
	}

	public void setup() {
		cam = new PeasyCam(this, 500);
		physics = new VerletPhysics();
		physics.addBehavior(new GravityBehavior(new Vec3D(0, windValue,
				gravityValue)));
		initmesh();
		render = new WB_Render(this);

//		surface = new Surface(this);
		gui = new Gui(this);
	}

	public void draw() {
		background(0);
		if (this.gravityOn)
			physics.update();
		mesh.run();
//		surface.run();
		gui.run();
		if (gui.cp5.window(this).isMouseOver()) {
			cam.setActive(false);
		} else {
			cam.setActive(true);
		}
	}

	void initmesh() {

		// Array of all vertices
		float[][] vertices = new float[numPart*numPart][3];
		int index = 0;
		for (int j = 0; j < numPart; j++) {
			for (int i = 0; i < numPart; i++) {
				vertices[index][0] = -totalLength / 4 + i * spacing / 2;
				vertices[index][1] = -totalLength / 4 + j * spacing / 2;
				vertices[index][2] = 0 + 0;
				index++;
			}
		}
		// Array of faces. Each face is an arry of vertex indices;
		index = 0;
		int[][] faces = new int[(numPart - 1) * (numPart - 1)][];

		for (int j = 0; j < numPart - 1; j++) {
			for (int i = 0; i < numPart - 1; i++) {
				faces[index] = new int[4];
				faces[index][0] = i + numPart * j;
				faces[index][1] = i + 1 + numPart * j;
				faces[index][2] = i + 1 + numPart * (j + 1);
				faces[index][3] = i + numPart * (j + 1);
				index++;
			}
		}

		HEC_FromFacelist facelistCreator = new HEC_FromFacelist()
				.setVertices(vertices).setFaces(faces).setDuplicate(false);
		mesh = new Mesh(this, facelistCreator);
		mesh.validate(true, true);
		mesh.collapseDegenerateEdges();
		//mesh.selection = new HE_Selection(mesh);

	}

	void GRAVITY(float gravitySlider) {
		if (frameCount > 0) {
			gravityValue = gravitySlider;
			physics.behaviors.clear();
			physics.addBehavior(new GravityBehavior(new Vec3D(0, windValue,
					gravityValue)));
			println("a slider event. setting GRAVITY to " + gravityValue);
		}
	}

	void WIND(float windSlider) {
		if (frameCount > 0) {
			windValue = windSlider;
			physics.behaviors.clear();
			physics.addBehavior(new GravityBehavior(new Vec3D(0, windValue,
					gravityValue)));
			println("a slider event. setting GRAVITY to " + gravityValue);
		}
	}

	void GRAVITYON() {
		gravityOn = !gravityOn;
		if (!gravityOn) {
			gui.gravityOn.setImages(gui.gravityOnImg1, gui.gravityOnImg2,
					gui.gravityOnImg3);
		} else {
			gui.gravityOn.setImages(gui.gravityOnImg3, gui.gravityOnImg2,
					gui.gravityOnImg1);
		}
	}

	public void keyPressed() {
		// if (key == 'l') {
		// if (!p1.isLocked())p1.lock(); // !p1.isLocked() = p1.isLocked==false
		// else p1.unlock(); //if (p1.isLocked())
		// println("'l' is pressed");
		// }
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { extrafabrica2015.ExtraFabrica2015.class
				.getName() });
	}
}
