import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import java.io.FileReader;

class MyTeacher implements ITeacher {
	// Prefer any plan that we believe will make the first observed element smaller
	public int compare(double[] beliefs, Plan planA, Plan planB, TransitionModel transitionModel, ObservationModel observationModel) {
		double[] endBeliefsA = transitionModel.getFinalBeliefs(beliefs, planA);
		double[] endBeliefsB = transitionModel.getFinalBeliefs(beliefs, planB);
		double[] endObsA = observationModel.beliefsToObservations(endBeliefsA);
		double[] endObsB = observationModel.beliefsToObservations(endBeliefsB);
		if(endObsA[0] < endObsB[0])
			return 1;
		else if(endObsB[0] < endObsA[0])
			return -1;
		else
			return 0;
	}
}

class Main {
	public static void testMarshaling() throws Exception {
		// Make an agent
		ManicAgent agent = new ManicAgent(new Random(1234), new MyTeacher(), 8, 3, 2);

		// Write it to a file
		JSONObject obj = agent.marshal();
		FileWriter file = new FileWriter("test.json");
		file.write(obj.toJSONString());
		file.close();

		// Read it from a file
		JSONParser parser = new JSONParser();
		JSONObject obj2 = (JSONObject)parser.parse(new FileReader("test.json"));
		ManicAgent agent2 = new ManicAgent(obj2, new Random(1234), new MyTeacher());
		
		System.out.println("passed");
	}


	public static void testNeuralNet() throws Exception {
		// Make some data
		Random rand = new Random(1234);
		Matrix features = new Matrix();
		features.setSize(1000, 2);
		Matrix labels = new Matrix();
		labels.setSize(1000, 2);
		for(int i = 0; i < 1000; i++) {
			
			double x = rand.nextDouble() * 2 - 1;
			double y = rand.nextDouble() * 2 - 1;
			features.row(i)[0] = x;
			features.row(i)[1] = y;
			labels.row(i)[0] = (y < x * x ? 0.9 : 0.1);
			labels.row(i)[1] = (x < y * y ? 0.1 : 0.9);
		}

		// Train on it
		NeuralNet nn = new NeuralNet();
		nn.layers.add(new Layer(2, 30));
		nn.layers.add(new Layer(30, 2));
		nn.init(rand);
		int iters = 10000000;
		double learningRate = 0.01;
		double lambda = 0.0001;
		for(int i = 0; i < iters; i++) {
			int index = rand.nextInt(features.rows());
			nn.regularize(learningRate, lambda);
			nn.trainIncremental(features.row(index), labels.row(index), 0.01);
			if(i % 1000000 == 0)
				System.out.println(Double.toString(((double)i * 100)/ iters) + "%");
		}

		// Visualize it
		for(int i = 0; i < nn.layers.size(); i++) {
			System.out.print("Layer " + Integer.toString(i) + ": ");
			for(int j = 0; j < nn.layers.get(i).hinge.length; j++)
				System.out.print(Double.toString(nn.layers.get(i).hinge[j]) + ", ");
			System.out.println();
		}
		BufferedImage image = new BufferedImage(100, 200, BufferedImage.TYPE_INT_ARGB);
		double[] in = new double[2];
		for(int y = 0; y < 100; y++) {
			for(int x = 0; x < 100; x++) {
				in[0] = ((double)x) / 100 * 2 - 1;
				in[1] = ((double)y) / 100 * 2 - 1;
				double[] out = nn.forwardProp(in);
				int g = Math.max(0, Math.min(255, (int)(out[0] * 256)));
				image.setRGB(x, y, new Color(g, g, g).getRGB());
				g = Math.max(0, Math.min(255, (int)(out[1] * 256)));
				image.setRGB(x, y + 100, new Color(g, g, g).getRGB());
			}
		}
		ImageIO.write(image, "png", new File("viz.png"));
	}
/*
	public static void main(String[] args) throw s Exception {
		//testNeuralNet();
		//testMarshaling();
		System.out.println("Please view getting_started.html in your favorite browser.");
	}
	*/
}
