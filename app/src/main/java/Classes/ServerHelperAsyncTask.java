package Classes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tasktodayappproject.MainActivity;
import com.example.tasktodayappproject.MenuActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Currency;

public class ServerHelperAsyncTask extends AsyncTask<String, Void, Void> {

    private Socket socket;
    private PrintWriter printWriter;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private char[] response;
    private String[] splitMessage;
    private String login_username,login_email;
    public Context context;
    public ServerHelperAsyncTask(Context context,String login_username,String login_email) {
    this.context=context;
    this.login_username=login_username;
    this.login_email=login_email;
    }

    @Override
    protected Void doInBackground(String... strings){
        try {

            socket = new Socket(strings[1], Integer.parseInt(strings[2]));
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(strings[0]);
            printWriter.flush();

            response = new char[500];
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.read(response);

            switch (splitMessage[0]) {
                case "PROCEED":
                    CurentUser.username=login_username;
                    CurentUser.user_email=login_email;
                    CurentUser.offlineMode=false;
                    Toast.makeText(context,"Logged in!",Toast.LENGTH_SHORT);
                    break;
                case "REGISTERED":
                    CurentUser.username=login_username;
                    CurentUser.user_email=login_email;
                    CurentUser.offlineMode=false;
                    Toast.makeText(context,"Registered!",Toast.LENGTH_SHORT);
                    break;
                case "NOTFOUND":
                    Toast.makeText(context,"Email not found!",Toast.LENGTH_SHORT);
                    break;
                case "WRONGPASSWORD":
                    Toast.makeText(context,"Wrong password!",Toast.LENGTH_SHORT);
                    break;
                case "ALREADYEXISTS":
                    Toast.makeText(context,"Email already exists!",Toast.LENGTH_SHORT);
                    break;
                default:
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
