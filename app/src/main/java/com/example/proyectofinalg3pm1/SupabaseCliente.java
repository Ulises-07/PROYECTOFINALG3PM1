package com.example.proyectofinalg3pm1;

import android.content.Context;

import io.supabase.common.interfaces.SupabaseJsonSerializer;
import io.supabase.gotrue.GoTrue;
import io.supabase.gotrue.GoTrueConfig;
import io.supabase.postgrest.Postgrest;
import io.supabase.realtime.Realtime;
import io.supabase.storage.Storage;
import io.supabase.supabase-kt.Supabase;
import io.supabase.supabase-kt.SupabaseImpl;
import io.supabase.supabase-kt.SupabaseKt;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;
import java.util.HashMap;

// Patrón Singleton para tener una sola instancia de Supabase en toda la app
public class SupabaseCliente {

    // ---- ¡CONFIGURA ESTO! ----
    // Pega tu URL y tu clave anónima de Supabase aquí
    // Las encuentras en Project Settings -> API
    private static final String SUPABASE_URL = "https://mnfbafsvpymtzryzhfwf.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1uZmJhZnN2cHltdHpyeXpoZndmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI3Mjk3MDUsImV4cCI6MjA3ODMwNTcwNX0.-xbEVtTBVAhG6ywzKnu7N-0q1JlFRLdpjgQhmap2KPg";
    // -------------------------

    private static Supabase cliente;

    // Usamos el Dispatcher de IO para las llamadas de red
    private static final CoroutineContext networkContext = Dispatchers.getIO();

    public static synchronized Supabase getInstancia(Context context) {
        if (cliente == null) {
            // Configuración para GoTrue (Autenticación)
            GoTrueConfig goTrueConfig = new GoTrueConfig(
                    networkContext,
                    false, // autoRefresh
                    true,  // initializeSession
                    null,  // localStorage
                    new HashMap<>(), // default headers
                    null   // clientName
            );

            // Inicializamos el cliente principal de Supabase
            // Es necesario usar el builder de esta manera para Java
            cliente = SupabaseKt.createSupabaseClient(
                    SUPABASE_URL,
                    SUPABASE_ANON_KEY,
                    GoTrue.Companion.create(SUPABASE_URL + "/auth/v1", SUPABASE_ANON_KEY, null, goTrueConfig, null),
                    Postgrest.Companion.create(SUPABASE_URL + "/rest/v1", SUPABASE_ANON_KEY, null, null, null, null, null),
                    Realtime.Companion.create(SUPABASE_URL.replace("http", "ws") + "/realtime/v1", SUPABASE_ANON_KEY, null, null, null),
                    Storage.Companion.create(SUPABASE_URL + "/storage/v1", SUPABASE_ANON_KEY, null, null, null),
                    networkContext,
                    (SupabaseJsonSerializer) null,
                    (Context) context
            );
        }
        return cliente;
    }
}
