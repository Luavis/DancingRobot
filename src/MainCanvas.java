import java.awt.GraphicsConfiguration;
import java.util.ArrayList;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class MainCanvas extends Canvas3D {
	
	private boolean spin = false;
	private SimpleUniverse u;
	private BoundingSphere bounds;
	private ArrayList<Animatable> animatableObjects;
	private TransformGroup worldGroup;
	private double worldScale = 0.3;
	
	public MainCanvas(GraphicsConfiguration config) {
		super(config);
		
		animatableObjects = new ArrayList<Animatable>();
		
	    // Create a simple scene and attach it to the virtual universe
	    BranchGroup scene = this.createSceneGraph();
	    u = new SimpleUniverse(this);
	 
	    // add mouse behaviors to the ViewingPlatform
	    ViewingPlatform viewingPlatform = u.getViewingPlatform();
	 
	    PlatformGeometry pg = new PlatformGeometry();
	 
	    // Set up the ambient light
	    Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
	    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	    ambientLightNode.setInfluencingBounds(bounds);
	    pg.addChild(ambientLightNode);
	 
	    // Set up the directional lights
	    Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
	    Vector3f light1Direction = new Vector3f(1.0f, 1.0f, 1.0f);
	    Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
	    Vector3f light2Direction = new Vector3f(-1.0f, -1.0f, -1.0f);
	 
	    DirectionalLight light1 = new DirectionalLight(light1Color,
	        light1Direction);
	    light1.setInfluencingBounds(bounds);
	    pg.addChild(light1);
	 
	    DirectionalLight light2 = new DirectionalLight(light2Color,
	        light2Direction);
	    light2.setInfluencingBounds(bounds);
	    pg.addChild(light2);
	 
	    viewingPlatform.setPlatformGeometry(pg);
	 
	    // This will move the ViewPlatform back a bit so the
	    // objects in the scene can be viewed.
	    viewingPlatform.setNominalViewingTransform();
	 
	    if (!spin) {
	      OrbitBehavior orbit = new OrbitBehavior(this,
	          OrbitBehavior.REVERSE_ALL);
	      BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0,
	          0.0), 100.0);
	      orbit.setSchedulingBounds(bounds);
	      viewingPlatform.setViewPlatformBehavior(orbit);
	    }
	 
	    u.addBranchGraph(scene);
	}
	
	public void addObjects() {
		Eva r = new Eva();
		r.setDance(new EvaDance(r));
		
		this.addObject(r.getSuperGroup());
		this.registerAnimatableObjects(r);
		
		Android a = new Android();
		a.setDance(new AndroidDance(a));
		
		this.addObject(a.getSuperGroup());
		this.registerAnimatableObjects(a);
		
		a.branch.transition(-1.5, 0, 0);
		r.branch.transition(1.5, 0, 0);
		
		a.startDancing();
		r.startDancing();
	}
	
	public void addObject(Group object) {
		worldGroup.addChild(object);
	}
	
	public void removeObject(BranchGroup object) {
		worldGroup.removeChild(object);
	}
	
	public void registerAnimatableObjects(Animatable animatable) {
		if(!this.animatableObjects.contains(animatable))
			this.animatableObjects.add(animatable);
	}
	
	public void unregisterAnimatableObjects(Animatable animatable) {
		if(this.animatableObjects.contains(animatable))
			this.animatableObjects.remove(animatable);
	}
	
	public BranchGroup createSceneGraph() {
	    // Create the root of the branch graph
	    BranchGroup objRoot = new BranchGroup();
	 
	    // Create a Transformgroup to scale all objects so they
	    // appear in the scene.
	    TransformGroup objScale = new TransformGroup();
	    Transform3D t3d = new Transform3D();
	    t3d.setScale(this.worldScale);
	    
	    objScale.setTransform(t3d);
	    objRoot.addChild(objScale);
	 
	    // Create the transform group node and initialize it to the
	    // identity. Enable the TRANSFORM_WRITE capability so that
	    // our behavior code can modify it at runtime. Add it to the
	    // root of the subgraph.
	    
	    worldGroup = new TransformGroup();
	    worldGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    worldGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    objScale.addChild(worldGroup);
	 
	    this.addObjects();
	    
	    bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
	 
	    if (spin) {
	      Transform3D yAxis = new Transform3D();
	      Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
	          4000, 0, 0, 0, 0, 0);
	 
	      RotationInterpolator rotator = new RotationInterpolator(
	          rotationAlpha, worldGroup, yAxis, 0.0f,
	          (float) Math.PI * 2.0f);
	      rotator.setSchedulingBounds(bounds);
	      worldGroup.addChild(rotator);
	    }
	 
	    // Set up the background
	    String backgroundPath = ResourceManager.getInstance().getPath(Constants.BACKGROUND_SPRITE);
	    TextureLoader backgroundTexture = new TextureLoader(backgroundPath, this);
	    
//	    Color3f bgColor = new Color3f(0.0f, 0.0f, 0.0f);
	    Background bgNode = new Background(backgroundTexture.getImage());
	    bgNode.setImageScaleMode(Background.SCALE_REPEAT);
	    bgNode.setApplicationBounds(bounds);
	    objRoot.addChild(bgNode);
	 
	    return objRoot;
	}
	
	public void animateAllObjectNextFrame() {
		for(Animatable animatableObjects : this.animatableObjects) {
			animatableObjects.animateNextFrame();
		}
	}		
}
