package com.unity3d.ads.placement;

import com.unity3d.ads.UnityAds.PlacementState;
import java.util.HashMap;

public class Placement {
    private static String _defaultPlacement;
    private static HashMap<String, PlacementState> _placementReadyMap;

    public static void setDefaultPlacement(String str) {
        _defaultPlacement = str;
    }

    public static void setPlacementState(String str, String str2) {
        if (_placementReadyMap == null) {
            _placementReadyMap = new HashMap();
        }
        _placementReadyMap.put(str, PlacementState.valueOf(str2));
    }

    public static boolean isReady(String str) {
        return getPlacementState(str) == PlacementState.READY;
    }

    public static boolean isReady() {
        return getPlacementState() == PlacementState.READY;
    }

    public static PlacementState getPlacementState(String str) {
        return currentState(str);
    }

    public static PlacementState getPlacementState() {
        if (_defaultPlacement == null) {
            return PlacementState.NOT_AVAILABLE;
        }
        return getPlacementState(_defaultPlacement);
    }

    public static void reset() {
        _placementReadyMap = null;
        _defaultPlacement = null;
    }

    public static String getDefaultPlacement() {
        return _defaultPlacement;
    }

    private static PlacementState currentState(String str) {
        if (_placementReadyMap == null || !_placementReadyMap.containsKey(str)) {
            return PlacementState.NOT_AVAILABLE;
        }
        return (PlacementState) _placementReadyMap.get(str);
    }
}
