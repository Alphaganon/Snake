import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_Z || key == KeyEvent.VK_UP) {
					if(tempObject.getVelY() != 1) {
						tempObject.setVelY(-1);
						tempObject.setVelX(0);
					}
				}
				else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					if(tempObject.getVelY() != -1) {
						tempObject.setVelY(1);
						tempObject.setVelX(0);
					}
				}
				else if(key == KeyEvent.VK_Q || key == KeyEvent.VK_LEFT) {
					if(tempObject.getVelX() != 1) {
						tempObject.setVelY(0);
						tempObject.setVelX(-1);
					}
				}
				else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					if(tempObject.getVelX() != -1) {
						tempObject.setVelY(0);
						tempObject.setVelX(1);
					}
				}
			}
		}

	}
	
	public void keyReleased(KeyEvent e) {
		
	}

}
