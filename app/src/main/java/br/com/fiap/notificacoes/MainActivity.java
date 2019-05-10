package br.com.fiap.notificacoes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNED_ID = "alertas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exibirNotificacao(View view) {

        // Instanciando notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationChannel notificationChannel =
                new NotificationChannel(CHANNED_ID, getString(R.string.notification_main), NotificationManager.IMPORTANCE_DEFAULT);

        // Descrição
        notificationChannel.setDescription(getString(R.string.notification_description));
        // Habilitar luz
        notificationChannel.enableLights(true);
        // Selecionar cor
        notificationChannel.setLightColor(Color.MAGENTA);
        // Habilitando e definido regra de vibração
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableVibration(true);

        notificationManager.createNotificationChannel(notificationChannel);

        // Criando o alerta efetivamente
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this, CHANNED_ID);

        notification.setContentTitle(getString(R.string.notification_title));
        notification.setChannelId(CHANNED_ID);
        notification.setContentText(getString(R.string.notification_text));
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentIntent(
                PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT));

        notificationManager.notify(100, notification.build());
    }
}
