package com.google.biometric;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                                              executor,
                                              new BiometricPrompt.AuthenticationCallback() {
                                                  @Override
                                                  public void onAuthenticationError(int errorCode,
                                                                                    @NonNull
                                                                                            CharSequence errString) {
                                                      super.onAuthenticationError(errorCode,
                                                                                  errString);
                                                      Toast.makeText(getApplicationContext(),
                                                                     "Authentication error: " + errString,
                                                                     Toast.LENGTH_SHORT)
                                                              .show();
                                                  }

                                                  @Override
                                                  public void onAuthenticationSucceeded(
                                                          @NonNull
                                                                  BiometricPrompt.AuthenticationResult result) {
                                                      super.onAuthenticationSucceeded(result);
                                                      Toast.makeText(getApplicationContext(),
                                                                     "Authentication succeeded!",
                                                                     Toast.LENGTH_SHORT)
                                                              .show();
                                                  }

                                                  @Override
                                                  public void onAuthenticationFailed() {
                                                      super.onAuthenticationFailed();
                                                      Toast.makeText(getApplicationContext(),
                                                                     "Authentication failed",
                                                                     Toast.LENGTH_SHORT)
                                                              .show();
                                                  }
                                              });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        AppCompatButton biometricLoginButton = findViewById(R.id.biometric_login);
        biometricLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }

}
