package extrafabrica2015;

import wblut.hemesh.HEC_FromFacelist;
import wblut.hemesh.HE_Mesh;
import wblut.hemesh.HE_Selection;

public class Mesh extends HE_Mesh {

	ExtraFabrica2015 p5;
	Surface surface;

	Mesh(ExtraFabrica2015 _p5, HEC_FromFacelist facelistCreator) {
		super(facelistCreator);
		p5 = _p5;
		surface = new Surface(p5);
	}

	void run() {
		surface.run();
		rendermesh();
	}

	private void rendermesh() {
		p5.noStroke();
		p5.fill(200);
		p5.render.drawFaces(this);
	}

}
