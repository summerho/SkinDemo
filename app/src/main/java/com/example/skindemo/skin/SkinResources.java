package com.example.skindemo.skin;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

import java.util.ArrayDeque;
import java.util.Locale;

public class SkinResources extends ResourcesWrapper {

    /**
     * cache 最大缓存size
     */
    private static final int LRU_CACHE_MAX_ITEMS = 2048;

    /**
     * debug tag
     */
    private static final String TAG = "SkinResources";

    /**
     * extra res
     */
    private final ArrayDeque<ResourceObj> mExtraRes = new ArrayDeque<>();

    /**
     * resources LRU cache
     */
    private final LruCache<Integer, Pair<Resources, Integer>> mResourceCache = new LruCache<>(LRU_CACHE_MAX_ITEMS);

    /**
     * 构造函数
     *
     * @param resources resources
     */
    SkinResources(Resources resources) {
        super(resources);
    }

    @Override
    public CharSequence getText(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getText(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getText(id);
    }

    @Override
    public CharSequence getQuantityText(int id, int quantity) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getQuantityText(pair.mSecond, quantity);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getQuantityText(id, quantity);
    }

    @Override
    public String getString(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getString(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getString(id);
    }

    @Override
    public String getString(int id, Object... formatArgs) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getString(pair.mSecond, formatArgs);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getString(id, formatArgs);
    }

    @Override
    public String getQuantityString(int id, int quantity, Object... formatArgs) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getQuantityString(pair.mSecond, quantity, formatArgs);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getQuantityString(id, quantity, formatArgs);
    }

    @Override
    public String getQuantityString(int id, int quantity) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getQuantityString(pair.mSecond, quantity);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getQuantityString(id, quantity);
    }

    @Override
    public CharSequence getText(int id, CharSequence def) {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getText(pair.mSecond, def);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getText(id, def);
    }

    @Override
    public CharSequence[] getTextArray(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getTextArray(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getTextArray(id);
    }

    @Override
    public String[] getStringArray(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getStringArray(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getStringArray(id);
    }

    @Override
    public int[] getIntArray(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getIntArray(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getIntArray(id);
    }

    @Override
    public float getDimension(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDimension(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDimension(id);
    }

    @Override
    public int getDimensionPixelOffset(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDimensionPixelOffset(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDimensionPixelOffset(id);
    }

    @Override
    public int getDimensionPixelSize(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDimensionPixelSize(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDimensionPixelSize(id);
    }

    @Override
    public float getFraction(int id, int base, int pbase) {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getFraction(pair.mSecond, base, pbase);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getFraction(id, base, pbase);
    }

    @Override
    @Deprecated
    public Drawable getDrawable(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDrawable(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDrawable(id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Drawable getDrawable(int id, Resources.Theme theme) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDrawable(pair.mSecond, theme);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDrawable(id, theme);
    }

    @Override
    @Deprecated
    public Drawable getDrawableForDensity(int id, int density) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDrawableForDensity(pair.mSecond, density);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDrawableForDensity(id, density);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Drawable getDrawableForDensity(int id, int density, Resources.Theme theme) {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getDrawableForDensity(pair.mSecond, density, theme);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getDrawableForDensity(id, density, theme);
    }

    @Override
    public int getColor(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getColor(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getColor(id);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public int getColor(int id, Resources.Theme theme) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getColor(pair.mSecond, theme);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getColor(id, theme);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public ColorStateList getColorStateList(int id, Resources.Theme theme) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getColorStateList(pair.mSecond, theme);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getColorStateList(id, theme);
    }

    @Override
    @Deprecated
    public ColorStateList getColorStateList(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getColorStateList(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getColorStateList(id);
    }

    @Override
    public boolean getBoolean(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getBoolean(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getBoolean(id);
    }

    @Override
    public int getInteger(int id) throws Resources.NotFoundException {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (checkPair(pair)) {
            try {
                // 尝试调用找到的资源id对
                return pair.mFirst.getInteger(pair.mSecond);
            } catch (Resources.NotFoundException e) {
                logOnException(e, id, pair);
            }
        }
        // 当调用找到的资源id对发生异常或mExtraRes为空时，
        // 直接调用系统资源，兜底厂商修改系统实现可能导致的问题
        return super.getInteger(id);
    }

    /**
     * 检查 res id pair 的合法性
     *
     * @param pair res id pair        String resType = getResourceTypeName(id);
     * @return 合法则为真
     */
    private boolean checkPair(Pair<Resources, Integer> pair) {
        return null != pair && null != pair.mFirst && pair.mSecond > 0;
    }

    /**
     * 根据主版包资源id，获得目标资源和id对
     *
     * @param id 主版包资源id
     * @return 目标资源和id对
     */
    public Pair<Resources, Integer> getResIdPair(int id) {
        Pair<Resources, Integer> pair = findStackedResIdPairWithCache(id);
        if (!checkPair(pair)) {
            pair = new Pair<>(getWrapperResouces(), id);
        }
        return pair;
    }

    /**
     * 查找资源id
     *
     * @param id id
     * @return 原始id对应皮肤包id
     */
    private int findStackedId(int id) {
        Pair<Resources, Integer> pair = findStackedResIdPair(id);
        return checkPair(pair) ? id : pair.mSecond;
    }

    /**
     * 带 cache 查找 extra 中对应的 res id pair
     * 如果 extra 中找不到则返回 null
     *
     * @param id id 原始 id
     * @return 返回 extra 中对应的 resource和id pair对
     */
    private Pair<Resources, Integer> findStackedResIdPairWithCache(int id) {
        if (mExtraRes.isEmpty()) {
            // 没有皮肤资源时直接返回空
            return null;
        }

        Pair<Resources, Integer> resourcePair = mResourceCache.get(id);
        if (resourcePair != null) {
            // 命中 cache
            Log.d(TAG, "hit cache. id: " + id + " resource pair id: " + resourcePair.mSecond);
            return resourcePair;
        }

        resourcePair = findStackedResIdPair(id);
        if (null == resourcePair) {
            // 如果ExtraResStack中找不到对应的项目，则cache系统的res id对，防止下次再查一遍ExtraResStack
            resourcePair = new Pair<>(getWrapperResouces(), id);
        }
        mResourceCache.put(id, resourcePair);
        return resourcePair;
    }

    /**
     * 查找 extra 中对应的 res id pair
     * 如果 extra 中找不到则返回 null
     *
     * @param id id 原始 id
     * @return 返回 extra 中对应的 resource和id pair对
     */
    private Pair<Resources, Integer> findStackedResIdPair(int id) {
        String resName = getResourceEntryName(id);
        String resType = getResourceTypeName(id);

        for (ResourceObj resource : mExtraRes) {
            if (!resource.isValid()) {
                continue;
            }
            int extraId = resource.getMRes().getIdentifier(resName, resType, resource.getMResPkgName());
            if (extraId != 0) {
                Log.d(TAG, String.format(Locale.getDefault(),
                        "invoke id(%d) in mExtra(%d) name(%s) type(%s)\n found id(%d) at res(%s)",
                        id, mExtraRes.size(), resName, resType, extraId, resource.toString()));
                return new Pair<>(resource.getMRes(), extraId);
            }
        }

        return null;
    }

    /**
     * 得到皮肤资源栈
     *
     * @return 皮肤资源栈
     */
    protected ArrayDeque<ResourceObj> getExtraRes() {
        return mExtraRes;
    }

    /**
     * log 异常
     */
    private void logOnException(Resources.NotFoundException e, int id, Pair<Resources, Integer> pair) {
        Log.d(TAG, Log.getStackTraceString(e));
        Log.d(TAG, "resource id : " + id);
        Log.d(TAG, "resource name : " + getResourceName(id));
        Log.d(TAG, "resource entryname : " + getResourceEntryName(id));
        Log.d(TAG, "resource packagename : " + getResourcePackageName(id));
        Log.d(TAG, "resrouce type : " + getResourceTypeName(id));
        int extraId = mExtraRes.getFirst().getMRes().getIdentifier(getResourceEntryName(id),
                getResourceTypeName(id),
                mExtraRes.getFirst().getMResPkgName());
        Log.d(TAG, "resource extraId: " + extraId);
        e.printStackTrace();
    }

    /**
     * push 添加皮肤resources
     *
     * @param resources resource obj
     */
    public void push(ResourceObj resources) {
        if (null != resources && resources.isValid() && !mExtraRes.contains(resources)) {
            mExtraRes.push(resources);
            mResourceCache.evictAll();
        }
    }

    /**
     * 清除皮肤相关resource和cache
     */
    public void clear() {
        mExtraRes.clear();
        mResourceCache.evictAll();
    }

}
