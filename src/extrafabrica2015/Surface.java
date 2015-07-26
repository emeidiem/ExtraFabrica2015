package extrafabrica2015;

import java.util.ArrayList;
import java.util.List;

import toxi.geom.Vec3D;
import toxi.physics.VerletSpring;
import wblut.hemesh.HE_Vertex;

public class Surface {

	ExtraFabrica2015 p5;
	ArrayList<Particle> particles = new ArrayList<Particle>();
	Particle head;

	Surface(ExtraFabrica2015 _p5) {
		p5 = _p5;
		createParticles();
		createSprings();
	}
	
	private void connectSurfacetoMesh() {
		for (int i=0; i<p5.mesh.numberOfVertices();i++){
			HE_Vertex v = p5.mesh.getVerticesAsList().get(i);
			Particle p = particles.get(i);
			v.x=p.x;
			v.y=p.y;
			v.z=p.z;
		}
		
	}

	void run(){
		renderParticles();
		renderSprings();
		connectSurfacetoMesh();
		//linkheadtoMouse();
	}

	private void createParticles() {
		int id = 0;
		for (int i = 0; i < p5.numPart; i++) {
			for (int j = 0; j < p5.numPart; j++) {
				Vec3D tempos = new Vec3D(i * p5.spacing/2 - p5.numPart * p5.spacing/2/2+p5.spacing/2/2, j * p5.spacing/2 - p5.numPart * p5.spacing/2/2+p5.spacing/2/2,
						0);
				Particle p = new Particle(p5, tempos, id);
				// Particle(ExtraFabrica00 _p5, Vec3D pos, int _id)
				p5.physics.addParticle(p);
				particles.add(p);
				if (id == 0)
					p.lock();
				if (id == p5.numPart - 1)
					p.lock();
				if (id == (p5.numPart - 1) * p5.numPart)
					p.lock();
				// p.lock();

				id++;
			}
		}
		head = particles.get(particles.size() - 1);
		head.lock();
	}
	
	private void createSprings() {
		for (int i = 0; i < particles.size(); i++) {
			if ((i > 0) && (i % p5.numPart != 0)) {
				Particle p1 = particles.get(i);
				Particle p2 = particles.get(i - 1);
				VerletSpring s = new VerletSpring(p1, p2, p5.spacing/2, 0.5f);
				p5.physics.addSpring(s);
			}
		}

		for (int i = 0; i < p5.numPart - 1; i++) {
			for (int j = 0; j < p5.numPart; j++) {
				// if (j>p5.numPart) {
				Particle p1 = particles.get(j + i * p5.numPart);
				Particle p2 = particles.get(j + (i + 1) * p5.numPart);
				VerletSpring s = new VerletSpring(p1, p2, p5.spacing/2, 0.5f);
				p5.physics.addSpring(s);
				// }
			}
		}
	}
	
	void renderParticles(){
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			p.display();
		}
	}
	
	void renderSprings() {
		for (int i = 0; i < p5.physics.springs.size(); i++) {
			VerletSpring sp = p5.physics.springs.get(i);
			p5.stroke(255);
			p5.strokeWeight(1);
			p5.line(sp.a.x, sp.a.y, sp.a.z, sp.b.x, sp.b.y, sp.b.z);
		}
	}

	void linkheadtoMouse() {

		head.set(p5.mouseX, p5.mouseY, 0);
	}

}
