package graphics.panels;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("DuplicatedCode")
public class Text3DPanel extends JPanel {
    private Text3D text;
    private Shape3D textShape;
    private PointLight firstLight;
    private PointLight secondLight;
    private ColoringAttributes firstPointColorAttributes;
    private ColoringAttributes secondPointColorAttributes;

    private TransformGroup firstPointTransformGroup, secondPointTransformGroup;

    private static final Point3f textStartPos = new Point3f(-0.5f, 0, 0);
    private static final Vector3d firstPointStartPos = new Vector3d(-2, 0, 0);
    private static final Vector3d secondPointStartPos = new Vector3d(2, 0, 1);
    private static final Color3f firstLightStartColor = new Color3f(Color.RED);
    private static final Color3f secondLightStartColor = new Color3f(Color.BLUE);
    private static final BoundingSphere influencingBounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);


    public Text3DPanel() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(1200, 650));

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        SimpleUniverse universe = new SimpleUniverse(canvas);


        BranchGroup branchRoot = new BranchGroup();
        TransformGroup sceneTransformGroup = new TransformGroup();
        Transform3D sceneTransform = new Transform3D();
        sceneTransform.setScale(0.35);
        sceneTransformGroup.setTransform(sceneTransform);
        branchRoot.addChild(sceneTransformGroup);


        initText3D();
        TransformGroup textTransformGroup = new TransformGroup();
        textTransformGroup.addChild(textShape);
        sceneTransformGroup.addChild(textTransformGroup);


        initFirstPointTransformGroup();
        sceneTransformGroup.addChild(firstPointTransformGroup);
        initSecondPointTransformGroup();
        sceneTransformGroup.addChild(secondPointTransformGroup);


        initFirstLight();
        initSecondLight();
        firstPointTransformGroup.addChild(firstLight);
        secondPointTransformGroup.addChild(secondLight);


        branchRoot.compile();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.getViewer().getView().setBackClipDistance(100.0);
        universe.addBranchGraph(branchRoot);

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if(text.getString().length() > 0){
                        text.setString(text.getString().substring(0, text.getString().length() - 1));
                    }
                } else if(Character.isLetterOrDigit(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())) {
                    text.setString(text.getString() + e.getKeyChar());
                }
            }
        });

        add(canvas, BorderLayout.CENTER);
    }

    public void initText3D(){
        Material m = new Material();
        Appearance a = new Appearance();

        m.setLightingEnable(true);
        a.setMaterial(m);

        Font3D f3d = new Font3D(new Font("Font", Font.ITALIC, 1),
                new FontExtrusion());

        text = new Text3D(f3d, "Text", textStartPos);
        text.setCapability(Text3D.ALLOW_STRING_WRITE);
        text.setCapability(Text3D.ALLOW_STRING_READ);

        textShape = new Shape3D();
        textShape.setGeometry(text);
        textShape.setAppearance(a);
    }

    public void initFirstPointTransformGroup(){
        Transform3D firstTransform = new Transform3D();
        firstTransform.set(firstPointStartPos);
        firstPointTransformGroup = new TransformGroup(firstTransform);
        firstPointTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        firstPointTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        firstPointColorAttributes = new ColoringAttributes();
        firstPointColorAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
        firstPointColorAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
        firstPointColorAttributes.setColor(firstLightStartColor);

        Appearance firstPointAppearance = new Appearance();
        firstPointAppearance.setColoringAttributes(firstPointColorAttributes);
        firstPointTransformGroup.addChild(new Sphere(0.15f, firstPointAppearance));

    }

    public void initSecondPointTransformGroup(){
        Transform3D secondTransform = new Transform3D();
        secondTransform.set(secondPointStartPos);
        secondPointTransformGroup = new TransformGroup(secondTransform);
        secondPointTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        secondPointTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        secondPointColorAttributes = new ColoringAttributes();
        secondPointColorAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
        secondPointColorAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
        secondPointColorAttributes.setColor(secondLightStartColor);

        Appearance secondPointAppearance = new Appearance();
        secondPointAppearance.setColoringAttributes(secondPointColorAttributes);
        secondPointTransformGroup.addChild(new Sphere(0.15f, secondPointAppearance));
    }

    public void initFirstLight(){
        Point3f firstPoint = new Point3f(firstPointStartPos);

        firstLight = new PointLight(firstLightStartColor, firstPoint, new Point3f(1, 0, 0));
        firstLight.setCapability(Light.ALLOW_COLOR_WRITE);
        firstLight.setCapability(Light.ALLOW_COLOR_READ);
        firstLight.setCapability(PointLight.ALLOW_POSITION_WRITE);
        firstLight.setCapability(PointLight.ALLOW_POSITION_READ);

        firstLight.setInfluencingBounds(influencingBounds);
    }

    public void initSecondLight(){
        Point3f secondPoint = new Point3f(secondPointStartPos);

        secondLight = new PointLight(secondLightStartColor, secondPoint, new Point3f(1, 0, 0));
        secondLight.setCapability(Light.ALLOW_COLOR_WRITE);
        secondLight.setCapability(Light.ALLOW_COLOR_READ);
        secondLight.setCapability(PointLight.ALLOW_POSITION_WRITE);
        secondLight.setCapability(PointLight.ALLOW_POSITION_READ);

        secondLight.setInfluencingBounds(influencingBounds);
    }


    public void setFirstLightColor(Color color){
        firstLight.setColor(new Color3f(color));
        firstPointColorAttributes.setColor(new Color3f(color));
    }

    public void setSecondLightColor(Color color){
        secondLight.setColor(new Color3f(color));
        secondPointColorAttributes.setColor(new Color3f(color));
    }

    public void setFirstLightPos(float x, float y, float z){
        Transform3D newTransform = new Transform3D();
        newTransform.set(new Vector3d(x, y, z));
        firstPointTransformGroup.setTransform(newTransform);
        firstLight.setPosition(x, y, z);
    }

    public void setSecondLightPos(float x, float y, float z){
        Transform3D newTransform = new Transform3D();
        newTransform.set(new Vector3d(x, y, z));
        secondPointTransformGroup.setTransform(newTransform);
        secondLight.setPosition(x, y, z);
    }
}
