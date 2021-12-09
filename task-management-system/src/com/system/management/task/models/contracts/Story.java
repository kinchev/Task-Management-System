package com.system.management.task.models.contracts;

import com.system.management.task.models.enums.Size;

public interface Story extends Task {
    Size getSize();

    void setSize(Size size);

    void changeSize(Size s);
}
