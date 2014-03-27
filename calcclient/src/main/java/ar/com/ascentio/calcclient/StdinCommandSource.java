package ar.com.ascentio.calcclient;

public class StdinCommandSource {

	private Context context;
	
	public StdinCommandSource(Context ctx) {
		this.context = ctx;
	}
	
	public Command getCommand() {
		return new QuitCommand(context);
	}
}