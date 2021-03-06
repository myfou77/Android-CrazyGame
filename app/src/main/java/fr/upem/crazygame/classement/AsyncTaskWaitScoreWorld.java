package fr.upem.crazygame.classement;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import fr.upem.crazygame.bytebuffer_manager.ByteBufferManager;
import fr.upem.crazygame.charset.CharsetServer;
import fr.upem.crazygame.searchgameactivity.SocketHandler;


/**
 * Cette classe de demander les scores mondiaux
 */
public class AsyncTaskWaitScoreWorld extends AsyncTask<Void, Void, List<Classement>> {
    private final ClassementActivity classementActivity;

    public AsyncTaskWaitScoreWorld(ClassementActivity classementActivity) {
        this.classementActivity = classementActivity;
    }


    @Override
    protected List<Classement> doInBackground(Void ... v) {
        SocketChannel sc = SocketHandler.getSocket();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ArrayList<Classement> classements = new ArrayList<>();

        boolean sendSucces = sendProtocol(sc, buffer);
        if (sendSucces) {
            try {
                int size = size(sc, buffer);
                int cpt = 0;
                //On lit tous les mots
                while (cpt != size) {
                    int sizeWord = size(sc, buffer);
                    String nameGame = word(sc, buffer, sizeWord);
                    int score = size(sc, buffer);
                    classements.add(new Classement(nameGame, score));
                    cpt++;
                }

                return classements;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... v) {
        super.onProgressUpdate();
    }

    @Override
    protected void onPostExecute(List<Classement> classements) {
        super.onPostExecute(classements);
        classementActivity.initList(classements);
    }


    private boolean sendProtocol (SocketChannel sc, ByteBuffer buffer) {
        buffer.putInt(4300);
        buffer.flip();
        try {
            sc.write(buffer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int size (SocketChannel sc, ByteBuffer buffer) throws IOException {
        buffer.clear();
        buffer.limit(4);
        if (ByteBufferManager.readFully(sc, buffer)) {
            buffer.flip();
            return buffer.getInt();
        }

        return -1;
    }

    private String word (SocketChannel sc, ByteBuffer buffer, int sizeWord) throws IOException {
        buffer.clear();
        buffer.limit(sizeWord);
        if (ByteBufferManager.readFully(sc, buffer)) {
            buffer.flip();
            return CharsetServer.CHARSET_UTF_8.decode(buffer).toString();
        }
        return null;
    }
}
