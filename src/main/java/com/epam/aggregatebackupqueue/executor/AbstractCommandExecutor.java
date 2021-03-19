package com.epam.aggregatebackupqueue.executor;

public abstract class AbstractCommandExecutor<COMMAND, RETURN> {
    public abstract RETURN execute(COMMAND command);
}
