package ru.mirea.shamrovio.mireaproject.ui.lesson7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtils {
	public static BufferedReader getReader(Socket s) throws IOException {
		return new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	public static PrintWriter getWrite(Socket s) throws IOException {
		return new PrintWriter(s.getOutputStream(), true);
	}
}