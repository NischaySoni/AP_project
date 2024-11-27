package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {

    private Body body;
    private Sprite groundSprite;  // Sprite to render the ground texture

    public Ground(World world) {
        Texture groundTexture = new Texture("Platform.png");

        // Create ground body (static)
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 10f);  // Position just above the bottom (half of the height of the ground)
        groundBodyDef.type = BodyDef.BodyType.StaticBody;  // Static body does not move

        body = world.createBody(groundBodyDef);

        // Create the shape of the ground (a simple rectangle covering the screen width)
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(Gdx.graphics.getWidth() / 2f, 10f);  // Width and height of the ground (height = 10f)

        // Create a fixture for the ground body
        body.createFixture(groundShape, 0f);  // No friction or density for the ground
        groundShape.dispose();  // Dispose of the shape as we no longer need it

        // Create a sprite to render the ground texture
        groundSprite = new Sprite(groundTexture);
        groundSprite.setSize(Gdx.graphics.getWidth(), 20f);  // Set the sprite size to match the ground
        groundSprite.setPosition(0, 10f);  // Position the sprite at the bottom-left corner
    }

    // Accessor to the body in case you need it for something else
    public Body getBody() {
        return body;
    }

    // Method to render the ground texture
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        groundSprite.draw(spriteBatch);  // Draw the ground texture
        spriteBatch.end();
    }
}
