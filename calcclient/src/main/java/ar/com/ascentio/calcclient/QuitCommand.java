package ar.com.ascentio.calcclient;

public class QuitCommand implements Command{

	//private Context context;
	
	public QuitCommand(Context ctx) {
		//this.context = ctx;
	}
	
	@Override
	public void execute() {
		System.exit(0);
	}
}
