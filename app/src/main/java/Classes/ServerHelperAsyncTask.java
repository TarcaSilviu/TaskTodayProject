package Classes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tasktodayappproject.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHelperAsyncTask extends AsyncTask<String, Void, Void> {

    private Socket socket;
    private PrintWriter printWriter;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private char[] response;
    private String[] splitMessage;
    public Context context;
    public ServerHelperAsyncTask() {
    }

    @Override
    protected Void doInBackground(String... strings) {
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

                    break;
                case "NOTFOUND":
                    Toast.makeText(context,"NOTFOUND!", Toast.LENGTH_SHORT);
                    break;
                case "WRONGPASSWORD":
                    Toast.makeText(context,"NOTFOUND!", Toast.LENGTH_SHORT);
                    break;
                case "ALREADYEXISTS":
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
