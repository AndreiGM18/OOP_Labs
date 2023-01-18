package ex2;

import java.util.EnumSet;

enum LogLevel {
    Info,
    Debug,
    Warning,
    Error,
    FunctionalMessage,
    FunctionalError;

    static EnumSet<LogLevel> all() {
        return EnumSet.allOf(LogLevel.class);
    }
}

abstract class LoggerBase {
    protected EnumSet<LogLevel> logMasks;
    protected LoggerBase next;

    public LoggerBase(EnumSet<LogLevel> logMasks) {
        this.logMasks = logMasks;
    }

    public LoggerBase setNext(LoggerBase nextLogger) {
        LoggerBase lastLogger = this;

        while (lastLogger.next != null)
            lastLogger = lastLogger.next;

        lastLogger.next = nextLogger;

        return this;
    }

    public void message(String message, LogLevel mask) {
        for (LogLevel logMask : logMasks) {
            if (mask.equals(logMask)) {
                writeMessage(message);
                break;
            }
        }

        if (next != null)
            next.message(message, mask);
    }

    abstract protected void writeMessage(String message);
}

class ConsoleLogger extends LoggerBase {
    public ConsoleLogger() {
        super(LogLevel.all());
    }

    @Override
    protected void writeMessage(String message) {
        String msg = "[Console] " + message;
        System.out.println(msg);
    }
}

class EmailLogger extends LoggerBase {
    public EmailLogger() {
        super(EnumSet.of(LogLevel.FunctionalMessage, LogLevel.FunctionalError));
    }

    @Override
    protected void writeMessage(String message) {
        String msg = "[Email] " + message;
        System.out.println(msg);
    }
}

class FileLogger extends LoggerBase {
    public FileLogger() {
        super(EnumSet.of(LogLevel.Warning, LogLevel.Error));
    }

    @Override
    protected void writeMessage(String message) {
        String msg = "[File] " + message;
        System.out.println(msg);
    }
}

public class Main {
    public static void main(final String[] args) {
        LoggerBase logger1 = new ConsoleLogger();
        LoggerBase logger2 = new EmailLogger();
        LoggerBase logger3 = new FileLogger();

        logger1.setNext(logger2).setNext(logger3);

        logger1.message("Se execută metoda ProcessOrder()", LogLevel.Debug);
        logger1.message("Comanda a fost procesată cu succes", LogLevel.Info);
        logger1.message("Datele despre adresa clientului lipsesc din baza de date a filialei", LogLevel.Warning);
        logger1.message("Detele despre adresa clientului lipsesc din baza de date a organizație", LogLevel.Error);
        logger1.message("Nu se poate procesa comanda #Comanda1 datată pe 25.11.2018 pentru clientul #Clientul1",
                LogLevel.FunctionalError);
        logger1.message("Comandă procesată", LogLevel.FunctionalMessage);

    }
}