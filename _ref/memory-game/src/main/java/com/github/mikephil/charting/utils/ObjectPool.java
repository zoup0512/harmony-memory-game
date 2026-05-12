package com.github.mikephil.charting.utils;

import com.mopub.volley.DefaultRetryPolicy;
import java.util.List;

public class ObjectPool<T extends Poolable> {
    private static int ids = 0;
    private int desiredCapacity;
    private T modelObject;
    private Object[] objects;
    private int objectsPointer;
    private int poolId;
    private float replenishPercentage;

    public static abstract class Poolable {
        public static int NO_OWNER = -1;
        int currentOwnerId = NO_OWNER;

        protected abstract Poolable instantiate();
    }

    public int getPoolId() {
        return this.poolId;
    }

    public static synchronized ObjectPool create(int withCapacity, Poolable object) {
        ObjectPool result;
        synchronized (ObjectPool.class) {
            result = new ObjectPool(withCapacity, object);
            result.poolId = ids;
            ids++;
        }
        return result;
    }

    private ObjectPool(int withCapacity, T object) {
        if (withCapacity <= 0) {
            throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
        }
        this.desiredCapacity = withCapacity;
        this.objects = new Object[this.desiredCapacity];
        this.objectsPointer = 0;
        this.modelObject = object;
        this.replenishPercentage = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        refillPool();
    }

    public void setReplenishPercentage(float percentage) {
        float p = percentage;
        if (p > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            p = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        } else if (p < 0.0f) {
            p = 0.0f;
        }
        this.replenishPercentage = p;
    }

    public float getReplenishPercentage() {
        return this.replenishPercentage;
    }

    private void refillPool() {
        refillPool(this.replenishPercentage);
    }

    private void refillPool(float percentage) {
        int portionOfCapacity = (int) (((float) this.desiredCapacity) * percentage);
        if (portionOfCapacity < 1) {
            portionOfCapacity = 1;
        } else if (portionOfCapacity > this.desiredCapacity) {
            portionOfCapacity = this.desiredCapacity;
        }
        for (int i = 0; i < portionOfCapacity; i++) {
            this.objects[i] = this.modelObject.instantiate();
        }
        this.objectsPointer = portionOfCapacity - 1;
    }

    public synchronized T get() {
        Poolable result;
        if (this.objectsPointer == -1 && this.replenishPercentage > 0.0f) {
            refillPool();
        }
        result = (Poolable) this.objects[this.objectsPointer];
        result.currentOwnerId = Poolable.NO_OWNER;
        this.objectsPointer--;
        return result;
    }

    public synchronized void recycle(T object) {
        if (object.currentOwnerId == Poolable.NO_OWNER) {
            this.objectsPointer++;
            if (this.objectsPointer >= this.objects.length) {
                resizePool();
            }
            object.currentOwnerId = this.poolId;
            this.objects[this.objectsPointer] = object;
        } else if (object.currentOwnerId == this.poolId) {
            throw new IllegalArgumentException("The object passed is already stored in this pool!");
        } else {
            throw new IllegalArgumentException("The object to recycle already belongs to poolId " + object.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
        }
    }

    public synchronized void recycle(List<T> objects) {
        while ((objects.size() + this.objectsPointer) + 1 > this.desiredCapacity) {
            resizePool();
        }
        int objectsListSize = objects.size();
        int i = 0;
        while (i < objectsListSize) {
            Poolable object = (Poolable) objects.get(i);
            if (object.currentOwnerId == Poolable.NO_OWNER) {
                object.currentOwnerId = this.poolId;
                this.objects[(this.objectsPointer + 1) + i] = object;
                i++;
            } else if (object.currentOwnerId == this.poolId) {
                throw new IllegalArgumentException("The object passed is already stored in this pool!");
            } else {
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + object.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
            }
        }
        this.objectsPointer += objectsListSize;
    }

    private void resizePool() {
        int oldCapacity = this.desiredCapacity;
        this.desiredCapacity *= 2;
        Object[] temp = new Object[this.desiredCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            temp[i] = this.objects[i];
        }
        this.objects = temp;
    }

    public int getPoolCapacity() {
        return this.objects.length;
    }

    public int getPoolCount() {
        return this.objectsPointer + 1;
    }
}
