package webb.todd.cannongame;

public class Target extends GameElement {

    private int hitReward;

    public Target(CannonView view, int color, int hitReward, int x, int y,
        int width, int length, float velocityY) {
        super(view, color, CannonView.TARGET_SOUND_ID, x, y, width, length,
        velocityY);
        this.hitReward = hitReward;
     }

    protected void onWallCollision(){
        super.onWallCollision(); // reverses this GameElement's velocity
        view.playSound( CannonView.REVERSAL_SOUND_ID, 0.2f, 1.8f );
    }

    public int getHitReward(){
        return hitReward;
    }
}
