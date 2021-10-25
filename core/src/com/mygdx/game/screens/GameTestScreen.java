package com.mygdx.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.*;
import com.mygdx.game.listeners.ACLGameListener;
import com.mygdx.game.systems.*;

public class GameTestScreen extends GameScreen {

    public GameTestScreen(ACLGame game) {
        super(game, game.getAssets());
        this.engine.addSystem(new RenderSystem(this.game.batcher));
        this.engine.addSystem(new MovementSystem());
        this.engine.addSystem(new HeroSystem());
        this.engine.addSystem(new PhysicsSystem());
        this.engine.addSystem(new DebugRenderSystem(this.game.batcher, this.game.camera));

        this.assets.getManager().finishLoading();

        createHero();
        createObstacle();
    }

    private void createHero(){

        Entity hero = new Entity();

        //Add Texture
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.setRegion(new TextureRegion(this.assets.getManager().get("sprites/cherry.png", Texture.class)));
        hero.add(textureComponent);


        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        //Add Position
        DirectionComponent directionComponent = new DirectionComponent();
        hero.add(directionComponent);

        MovementComponent movementComponent = new MovementComponent(HeroComponent.HERO_VELOCITY);
        hero.add(movementComponent);

        HeroComponent heroComponent = new HeroComponent();
        hero.add(heroComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(8, 8);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16, 16);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addDynamicBody(8, -32, 32, 16);

        hero.add(new BodyComponent(body));



        this.engine.addEntity(hero);
    }

    private void createObstacle(){

        Entity hero = new Entity();

        //Add Position
        TransformComponent transformComponent = new TransformComponent(new Vector3(10,10,10));
        hero.add(transformComponent);

        hero.add(transformComponent);


        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(8, -32);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 16);

        PhysicsSystem physicsSystem = this.engine.getSystem(PhysicsSystem.class);

        Body body = physicsSystem.addStaticBody(8, -32, 32, 16);

        hero.add(new BodyComponent(body));



        this.engine.addEntity(hero);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.input.setInputProcessor(new ACLGameListener(this));

    }
}

