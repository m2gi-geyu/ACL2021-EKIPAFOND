package com.mygdx.game.factory.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Assets;
import com.mygdx.game.World;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.physics.PhysicsSystem;

public class TrapBuilder implements EntityBuilder{

    private Assets assets;
    private PhysicsSystem physicsSystem;
    private Body body ;

    public TrapBuilder(Assets assetManager, PhysicsSystem physicsSystem) {
        this.assets = assetManager;
        this.physicsSystem = physicsSystem;
    }

    @Override
    public Entity buildEntity(float x, float y) {

        Entity treasure = new Entity();

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(assets.getManager().get("tiles/fire.png", Texture.class)));
        treasure.add(textureComponent);

        TransformComponent transformComponent = new TransformComponent(new Vector3(x , y,0));
        treasure.add(transformComponent);

        body = physicsSystem.addSensorBody(x , y, World.CASE_DIMENSION,World.CASE_DIMENSION);
        body.setUserData(treasure);
        //System.out.print("  Treasure  ");
        treasure.add(new SteeringComponent(body));
        treasure.add(new CollisionComponent());
        treasure.add(new TypeComponent(TypeComponent.TYPE_TRAP));
        return treasure;
    }
}