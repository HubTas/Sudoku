package pl.first.firstjava;

import java.beans.PropertyChangeListener;

public interface Observer {

    void registerChangeListener(PropertyChangeListener newListener);

    void removeChangeListener(PropertyChangeListener newListener);
}