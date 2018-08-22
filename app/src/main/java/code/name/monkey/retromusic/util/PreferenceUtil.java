package code.name.monkey.retromusic.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import code.name.monkey.retromusic.R;
import code.name.monkey.retromusic.RetroApplication;
import code.name.monkey.retromusic.helper.SortOrder;
import code.name.monkey.retromusic.model.CategoryInfo;
import code.name.monkey.retromusic.ui.fragments.NowPlayingScreen;
import code.name.monkey.retromusic.ui.fragments.mainactivity.folders.FoldersFragment;

public final class PreferenceUtil {

    public static final String KEEP_SCREEN_ON = "keep_screen_on";
    public static final String TOGGLE_HOME_BANNER = "toggle_home_banner";
    public static final String NOW_PLAYING_SCREEN_ID = "now_playing_screen_id";
    public static final String CAROUSEL_EFFECT = "carousel_effect";
    public static final String COLORED_NOTIFICATION = "colored_notification";
    public static final String CLASSIC_NOTIFICATION = "classic_notification";
    public static final String GAPLESS_PLAYBACK = "gapless_playback";
    public static final String ALBUM_ART_ON_LOCKSCREEN = "album_art_on_lockscreen";
    public static final String BLURRED_ALBUM_ART = "blurred_album_art";
    public static final String TOGGLE_HEADSET = "toggle_headset";
    public static final String DOMINANT_COLOR = "dominant_color";
    public static final String GENERAL_THEME = "general_theme";
    public static final String CIRCULAR_ALBUM_ART = "circular_album_art";
    public static final String USER_NAME = "user_name";
    public static final String TOGGLE_FULL_SCREEN = "toggle_full_screen";
    public static final String TOGGLE_VOLUME = "toggle_volume";
    public static final String TOGGLE_TAB_TITLES = "toggle_tab_titles";
    public static final String ROUND_CORNERS = "corner_window";
    public static final String TOGGLE_GENRE = "toggle_genre";
    public static final String PROFILE_IMAGE_PATH = "profile_image_path";
    public static final String BANNER_IMAGE_PATH = "banner_image_path";
    public static final String ADAPTIVE_COLOR_APP = "adaptive_color_app";
    public static final String TOGGLE_SEPARATE_LINE = "toggle_separate_line";
    public static final String ALBUM_GRID_STYLE = "album_grid_style";
    public static final String ARTIST_GRID_STYLE = "artist_grid_style";
    public static final String TOGGLE_ADD_CONTROLS = "toggle_add_controls";
    private static final String GENRE_SORT_ORDER = "genre_sort_order";
    private static final String LIBRARY_CATEGORIES = "library_categories";
    private static final String LAST_PAGE = "last_start_page";
    private static final String LAST_MUSIC_CHOOSER = "last_music_chooser";
    private static final String DEFAULT_START_PAGE = "default_start_page";
    private static final String INITIALIZED_BLACKLIST = "initialized_blacklist";
    private static final String ARTIST_SORT_ORDER = "artist_sort_order";
    private static final String ARTIST_SONG_SORT_ORDER = "artist_song_sort_order";
    private static final String ARTIST_ALBUM_SORT_ORDER = "artist_album_sort_order";
    private static final String ALBUM_SORT_ORDER = "album_sort_order";
    private static final String ALBUM_SONG_SORT_ORDER = "album_song_sort_order";
    private static final String SONG_SORT_ORDER = "song_sort_order";
    private static final String ALBUM_GRID_SIZE = "album_grid_size";
    private static final String ALBUM_GRID_SIZE_LAND = "album_grid_size_land";
    private static final String SONG_GRID_SIZE = "song_grid_size";
    private static final String SONG_GRID_SIZE_LAND = "song_grid_size_land";
    private static final String ARTIST_GRID_SIZE = "artist_grid_size";
    private static final String ARTIST_GRID_SIZE_LAND = "artist_grid_size_land";
    private static final String ALBUM_COLORED_FOOTERS = "album_colored_footers";
    private static final String SONG_COLORED_FOOTERS = "song_colored_footers";
    private static final String ARTIST_COLORED_FOOTERS = "artist_colored_footers";
    private static final String ALBUM_ARTIST_COLORED_FOOTERS = "album_artist_colored_footers";
    private static final String COLORED_APP_SHORTCUTS = "colored_app_shortcuts";
    private static final String AUDIO_DUCKING = "audio_ducking";
    private static final String LAST_ADDED_CUTOFF = "last_added_interval";
    private static final String LAST_SLEEP_TIMER_VALUE = "last_sleep_timer_value";
    private static final String NEXT_SLEEP_TIMER_ELAPSED_REALTIME = "next_sleep_timer_elapsed_real_time";
    private static final String IGNORE_MEDIA_STORE_ARTWORK = "ignore_media_store_artwork";
    private static final String LAST_CHANGELOG_VERSION = "last_changelog_version";
    private static final String INTRO_SHOWN = "intro_shown";
    private static final String AUTO_DOWNLOAD_IMAGES_POLICY = "auto_download_images_policy";
    private static final String START_DIRECTORY = "start_directory";
    private static final String SYNCHRONIZED_LYRICS_SHOW = "synchronized_lyrics_show";
    private static final String LOCK_SCREEN = "lock_screen";
    private static final String ALBUM_DETAIL_SONG_SORT_ORDER = "album_detail_song_sort_order";
    private static final String ARTIST_DETAIL_SONG_SORT_ORDER = "artist_detail_song_sort_order";
    private static final String LYRICS_OPTIONS = "lyrics_options";
    private static final String SAF_SDCARD_URI = "saf_sdcard_uri";
    private static final String DOCUMENT_TREE_URI = "document_tree_uri";
    private static final String CHOOSE_EQUALIZER = "choose_equalizer";
    private static final String TOGGLE_SHUFFLE = "toggle_shuffle";
    private static final String SONG_GRID_STYLE = "song_grid_style";
    private static final String TOGGLE_ANIMATIONS = "toggle_animations";
    private static final String TAG = "PreferenceUtil";
    private static final String LAST_KNOWN_LYRICS_TYPE = "LAST_KNOWN_LYRICS_TYPE";
    private static PreferenceUtil sInstance;
    private final SharedPreferences mPreferences;

    private PreferenceUtil(@NonNull final Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferenceUtil getInstance(@NonNull final Context context) {
        if (sInstance == null) {
            sInstance = new PreferenceUtil(context.getApplicationContext());
        }
        return sInstance;
    }

    @StyleRes
    public static int getThemeResFromPrefValue(String themePrefValue) {
        switch (themePrefValue) {
            case "dark":
                return R.style.Theme_RetroMusic;
            case "color":
                return R.style.Theme_RetroMusic_Color;
            case "acolor":
                return R.style.Theme_RetroMusic_Black;
            case "black":
                return R.style.Theme_RetroMusic_Black;
            case "light":
            default:
                return R.style.Theme_RetroMusic_Light;
        }
    }

    public int getAlbumDetailsPageStyle() {

        TypedArray typedArray = RetroApplication.getInstance().
                getResources().obtainTypedArray(R.array.pref_album_detail_style_values);
        int layout = typedArray.getResourceId(mPreferences.getInt("album_detail_style", 0), -1);
        typedArray.recycle();
        return layout;
    }

    public final String getArtistSortOrder() {
        return mPreferences.getString(ARTIST_SORT_ORDER, SortOrder.ArtistSortOrder.ARTIST_A_Z);
    }

    public void setArtistSortOrder(final String sortOrder) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(ARTIST_SORT_ORDER, sortOrder);
        editor.apply();
    }

    public final String getArtistSongSortOrder() {
        return mPreferences.getString(ARTIST_SONG_SORT_ORDER, SortOrder.ArtistSongSortOrder.SONG_A_Z);
    }

    public final boolean toggleHomeBanner() {
        return mPreferences.getBoolean(TOGGLE_HOME_BANNER, false);
    }

    public final String getArtistAlbumSortOrder() {
        return mPreferences
                .getString(ARTIST_ALBUM_SORT_ORDER, SortOrder.ArtistAlbumSortOrder.ALBUM_YEAR);
    }

    public final String getAlbumSortOrder() {
        return mPreferences.getString(ALBUM_SORT_ORDER, SortOrder.AlbumSortOrder.ALBUM_A_Z);
    }

    public void setAlbumSortOrder(final String sortOrder) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(ALBUM_SORT_ORDER, sortOrder);
        editor.apply();
    }

    public final String getAlbumSongSortOrder() {
        return mPreferences
                .getString(ALBUM_SONG_SORT_ORDER, SortOrder.AlbumSongSortOrder.SONG_TRACK_LIST);
    }

    public final String getSongSortOrder() {
        return mPreferences.getString(SONG_SORT_ORDER, SortOrder.SongSortOrder.SONG_A_Z);
    }

    public void setSongSortOrder(final String sortOrder) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(SONG_SORT_ORDER, sortOrder);
        editor.apply();
    }

    public final String getGenreSortOrder() {
        return mPreferences.getString(GENRE_SORT_ORDER, SortOrder.GenreSortOrder.GENRE_A_Z);
    }

    public boolean isScreenOnEnabled() {
        return mPreferences.getBoolean(KEEP_SCREEN_ON, false);
    }

    public void setInitializedBlacklist() {
        final Editor editor = mPreferences.edit();
        editor.putBoolean(INITIALIZED_BLACKLIST, true);
        editor.apply();
    }

    public final boolean initializedBlacklist() {
        return mPreferences.getBoolean(INITIALIZED_BLACKLIST, false);
    }

    public boolean isExtraMiniExtraControls() {
        return mPreferences.getBoolean(TOGGLE_ADD_CONTROLS, false);
    }

    public boolean circularAlbumArt() {
        return mPreferences.getBoolean(CIRCULAR_ALBUM_ART, false);
    }

    public boolean carouselEffect() {
        return mPreferences.getBoolean(CAROUSEL_EFFECT, false);
    }

    public ArrayList<CategoryInfo> getLibraryCategoryInfos() {
        String data = mPreferences.getString(LIBRARY_CATEGORIES, null);
        if (data != null) {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<CategoryInfo>>() {
            }.getType();

            try {
                return gson.fromJson(data, collectionType);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }

        return getDefaultLibraryCategoryInfos();
    }

    public void setLibraryCategoryInfos(ArrayList<CategoryInfo> categories) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<CategoryInfo>>() {
        }.getType();

        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(LIBRARY_CATEGORIES, gson.toJson(categories, collectionType));
        editor.apply();
    }

    public ArrayList<CategoryInfo> getDefaultLibraryCategoryInfos() {
        ArrayList<CategoryInfo> defaultCategoryInfos = new ArrayList<>(5);
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.SONGS, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.ALBUMS, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.ARTISTS, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.GENRES, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.PLAYLISTS, true));
        return defaultCategoryInfos;
    }

    public boolean isRoundCorners() {
        return mPreferences.getBoolean(ROUND_CORNERS, false);
    }

    public void registerOnSharedPreferenceChangedListener(
            SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener) {
        mPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangedListener(
            SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener) {
        mPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    public final int getDefaultStartPage() {
        return Integer.parseInt(mPreferences.getString(DEFAULT_START_PAGE, "-1"));
    }


    public final int getLastPage() {
        return mPreferences.getInt(LAST_PAGE, 0);
    }

    public void setLastPage(final int value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(LAST_PAGE, value);
        editor.apply();
    }

    public int getLastLyricsType() {
        return mPreferences.getInt(LAST_KNOWN_LYRICS_TYPE, R.id.normal_lyrics);
    }

    public void setLastLyricsType(int group) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(LAST_KNOWN_LYRICS_TYPE, group);
        editor.apply();
    }

    public final int getLastMusicChooser() {
        return mPreferences.getInt(LAST_MUSIC_CHOOSER, 0);
    }

    public void setLastMusicChooser(final int value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(LAST_MUSIC_CHOOSER, value);
        editor.apply();
    }

    public final boolean coloredNotification() {
        return mPreferences.getBoolean(COLORED_NOTIFICATION, true);
    }

    public final boolean classicNotification() {
        return mPreferences.getBoolean(CLASSIC_NOTIFICATION, false);
    }

    public void setClassicNotification(final boolean value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(CLASSIC_NOTIFICATION, value);
        editor.apply();
    }

    public final NowPlayingScreen getNowPlayingScreen() {
        int id = mPreferences.getInt(NOW_PLAYING_SCREEN_ID, 0);
        for (NowPlayingScreen nowPlayingScreen : NowPlayingScreen.values()) {
            if (nowPlayingScreen.id == id) {
                return nowPlayingScreen;
            }
        }
        return NowPlayingScreen.NORMAL;
    }

    @SuppressLint("CommitPrefEdits")
    public void setNowPlayingScreen(NowPlayingScreen nowPlayingScreen) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(NOW_PLAYING_SCREEN_ID, nowPlayingScreen.id);
        editor.apply();
    }

    public void setColoredAppShortcuts(final boolean value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(COLORED_APP_SHORTCUTS, value);
        editor.apply();
    }

    public final boolean coloredAppShortcuts() {
        return mPreferences.getBoolean(COLORED_APP_SHORTCUTS, true);
    }

    public final boolean gaplessPlayback() {
        return mPreferences.getBoolean(GAPLESS_PLAYBACK, false);
    }

    public final boolean audioDucking() {
        return mPreferences.getBoolean(AUDIO_DUCKING, true);
    }

    public final boolean albumArtOnLockscreen() {
        return mPreferences.getBoolean(ALBUM_ART_ON_LOCKSCREEN, true);
    }

    public final boolean blurredAlbumArt() {
        return mPreferences.getBoolean(BLURRED_ALBUM_ART, false);
    }

    public final boolean ignoreMediaStoreArtwork() {
        return mPreferences.getBoolean(IGNORE_MEDIA_STORE_ARTWORK, false);
    }


    public int getLastSleepTimerValue() {
        return mPreferences.getInt(LAST_SLEEP_TIMER_VALUE, 30);
    }

    public void setLastSleepTimerValue(final int value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(LAST_SLEEP_TIMER_VALUE, value);
        editor.apply();
    }

    public long getNextSleepTimerElapsedRealTime() {
        return mPreferences.getLong(NEXT_SLEEP_TIMER_ELAPSED_REALTIME, -1);
    }

    public void setNextSleepTimerElapsedRealtime(final long value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(NEXT_SLEEP_TIMER_ELAPSED_REALTIME, value);
        editor.apply();
    }

    public final int getAlbumGridSize(Context context) {
        return mPreferences
                .getInt(ALBUM_GRID_SIZE, context.getResources().getInteger(R.integer.default_grid_columns));
    }

    public void setSongGridSize(final int gridSize) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(SONG_GRID_SIZE, gridSize);
        editor.apply();
    }

    public final int getSongGridSize(Context context) {
        return mPreferences
                .getInt(SONG_GRID_SIZE, context.getResources().getInteger(R.integer.default_list_columns));
    }

    public void setArtistGridSize(final int gridSize) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(ARTIST_GRID_SIZE, gridSize);
        editor.apply();
    }

    public final int getArtistGridSize(Context context) {
        return mPreferences.getInt(ARTIST_GRID_SIZE,
                context.getResources().getInteger(R.integer.default_list_artist_columns));
    }

    public void setAlbumGridSizeLand(final int gridSize) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(ALBUM_GRID_SIZE_LAND, gridSize);
        editor.apply();
    }

    public final int getAlbumGridSizeLand(Context context) {
        return mPreferences.getInt(ALBUM_GRID_SIZE_LAND,
                context.getResources().getInteger(R.integer.default_grid_columns_land));
    }

    public void setSongGridSizeLand(final int gridSize) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(SONG_GRID_SIZE_LAND, gridSize);
        editor.apply();
    }

    public final int getSongGridSizeLand(Context context) {
        return mPreferences.getInt(SONG_GRID_SIZE_LAND,
                context.getResources().getInteger(R.integer.default_list_columns_land));
    }

    public void setArtistGridSizeLand(final int gridSize) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(ARTIST_GRID_SIZE_LAND, gridSize);
        editor.apply();
    }

    public final int getArtistGridSizeLand(Context context) {
        return mPreferences.getInt(ARTIST_GRID_SIZE_LAND,
                context.getResources().getInteger(R.integer.default_list_artist_columns_land));
    }

    public void setAlbumGridSize(final int gridSize) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(ALBUM_GRID_SIZE, gridSize);
        editor.apply();
    }

    public void setAlbumColoredFooters(final boolean value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(ALBUM_COLORED_FOOTERS, value);
        editor.apply();
    }

    public final boolean albumColoredFooters() {
        return mPreferences.getBoolean(ALBUM_COLORED_FOOTERS, false);
    }

    public void setAlbumArtistColoredFooters(final boolean value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(ALBUM_ARTIST_COLORED_FOOTERS, value);
        editor.apply();
    }

    public final boolean albumArtistColoredFooters() {
        return mPreferences.getBoolean(ALBUM_ARTIST_COLORED_FOOTERS, true);
    }

    public void setSongColoredFooters(final boolean value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(SONG_COLORED_FOOTERS, value);
        editor.apply();
    }

    public final boolean songColoredFooters() {
        return mPreferences.getBoolean(SONG_COLORED_FOOTERS, false);
    }

    public void setArtistColoredFooters(final boolean value) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(ARTIST_COLORED_FOOTERS, value);
        editor.apply();
    }

    public final boolean artistColoredFooters() {
        return mPreferences.getBoolean(ARTIST_COLORED_FOOTERS, true);
    }

    public void setLastChangeLogVersion(int version) {
        mPreferences.edit().putInt(LAST_CHANGELOG_VERSION, version).apply();
    }

    public final int getLastChangelogVersion() {
        return mPreferences.getInt(LAST_CHANGELOG_VERSION, -1);
    }

    @SuppressLint("CommitPrefEdits")
    public void setIntroShown() {
        // don't use apply here
        mPreferences.edit().putBoolean(INTRO_SHOWN, true).commit();
    }

    public final File getStartDirectory() {
        return new File(mPreferences
                .getString(START_DIRECTORY, FoldersFragment.getDefaultStartDirectory().getPath()));
    }

    public void setStartDirectory(File file) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(START_DIRECTORY, FileUtil.safeGetCanonicalPath(file));
        editor.apply();
    }

    public final boolean introShown() {
        return mPreferences.getBoolean(INTRO_SHOWN, false);
    }

    public final String autoDownloadImagesPolicy() {
        return mPreferences.getString(AUTO_DOWNLOAD_IMAGES_POLICY, "only_wifi");
    }

    public final boolean synchronizedLyricsShow() {
        return mPreferences.getBoolean(SYNCHRONIZED_LYRICS_SHOW, true);
    }

    public int getGeneralTheme() {
        return getThemeResFromPrefValue(mPreferences.getString(GENERAL_THEME, "light"));
    }

    public void setGeneralTheme(String theme) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(GENERAL_THEME, theme);
        editor.apply();
    }

    public long getLastAddedCutoff() {
        final CalendarUtil calendarUtil = new CalendarUtil();
        long interval;

        switch (mPreferences.getString(LAST_ADDED_CUTOFF, "")) {
            case "today":
                interval = calendarUtil.getElapsedToday();
                break;
            case "this_week":
                interval = calendarUtil.getElapsedWeek();
                break;
            case "past_three_months":
                interval = calendarUtil.getElapsedMonths(3);
                break;
            case "this_year":
                interval = calendarUtil.getElapsedYear();
                break;
            case "this_month":
            default:
                interval = calendarUtil.getElapsedMonth();
                break;
        }

        return (System.currentTimeMillis() - interval) / 1000;
    }

    public boolean getAdaptiveColor() {
        return mPreferences.getBoolean(ADAPTIVE_COLOR_APP, false);
    }

    public boolean getLockScreen() {
        return mPreferences.getBoolean(LOCK_SCREEN, false);
    }

    public String getUserName() {
        return mPreferences.getString(USER_NAME, "User");
    }

    public void setUserName(String name) {
        mPreferences.edit().putString(USER_NAME, name).apply();
    }

    public boolean getFullScreenMode() {
        return mPreferences.getBoolean(TOGGLE_FULL_SCREEN, false);
    }

    public void setFullScreenMode(int newValue) {
        mPreferences.edit().putInt(TOGGLE_FULL_SCREEN, newValue).apply();
    }

    public String lyricsOptions() {
        return mPreferences.getString(LYRICS_OPTIONS, "offline");
    }

    public void saveProfileImage(String profileImagePath) {
        mPreferences.edit().putString(PROFILE_IMAGE_PATH, profileImagePath)
                .apply();

    }

    public String getProfileImage() {
        return mPreferences.getString(PROFILE_IMAGE_PATH, "");
    }

    public String getBannerImage() {
        return mPreferences.getString(BANNER_IMAGE_PATH, "");
    }

    public void setBannerImagePath(String bannerImagePath) {
        mPreferences.edit().putString(BANNER_IMAGE_PATH, bannerImagePath)
                .apply();
    }

    public String getAlbumDetailSongSortOrder() {
        return mPreferences
                .getString(ALBUM_DETAIL_SONG_SORT_ORDER, SortOrder.AlbumSongSortOrder.SONG_TRACK_LIST);
    }

    public void setAlbumDetailSongSortOrder(String sortOrder) {
        Editor edit = this.mPreferences.edit();
        edit.putString(ALBUM_DETAIL_SONG_SORT_ORDER, sortOrder);
        edit.apply();
    }

    public String getArtistDetailSongSortOrder() {
        return mPreferences
                .getString(ARTIST_DETAIL_SONG_SORT_ORDER, SortOrder.ArtistSongSortOrder.SONG_A_Z);
    }

    public void setArtistDetailSongSortOrder(String sortOrder) {
        Editor edit = this.mPreferences.edit();
        edit.putString(ARTIST_DETAIL_SONG_SORT_ORDER, sortOrder);
        edit.apply();
    }

    public boolean getVolumeToggle() {
        return mPreferences.getBoolean(TOGGLE_VOLUME, false);
    }

    public int getLyricsOptions() {
        return mPreferences.getInt(LYRICS_OPTIONS, 1);
    }

    public void setLyricsOptions(int i) {
        mPreferences.edit().putInt(LYRICS_OPTIONS, i).apply();
    }

    public boolean getHeadsetPlugged() {
        return mPreferences.getBoolean(TOGGLE_HEADSET, false);
    }

    public boolean tabTitles() {
        return mPreferences.getBoolean(TOGGLE_TAB_TITLES, true);
    }

    public boolean isDominantColor() {
        return mPreferences.getBoolean(DOMINANT_COLOR, false);
    }

    public boolean isGenreShown() {
        return mPreferences.getBoolean(TOGGLE_GENRE, false);
    }

    public String getSelectedEqualizer() {
        return mPreferences.getString(CHOOSE_EQUALIZER, "system");
    }

    public boolean isShuffleModeOn() {
        return mPreferences.getBoolean(TOGGLE_SHUFFLE, false);
    }

    public void resetCarouselEffect() {
        mPreferences.edit().putBoolean(CAROUSEL_EFFECT, false).apply();
    }

    public void resetCircularAlbumArt() {
        mPreferences.edit().putBoolean(CIRCULAR_ALBUM_ART, false).apply();
    }

    @LayoutRes
    public int getAlbumGridStyle(Context context) {
        int pos = Integer.parseInt(mPreferences.getString(ALBUM_GRID_STYLE, "0"));
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.pref_grid_style_layout);
        int layoutRes = typedArray.getResourceId(pos, -1);
        typedArray.recycle();
        if (layoutRes == -1) {
            return R.layout.item_card;
        }
        return layoutRes;
    }

    public void setAlbumGridStyle(int type) {
        mPreferences.edit().putInt(ALBUM_GRID_STYLE, type).apply();
    }

    public int getArtistGridStyle(Context context) {
        int pos = Integer.parseInt(mPreferences.getString(ARTIST_GRID_STYLE, "0"));
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.pref_grid_style_layout);
        int layoutRes = typedArray.getResourceId(pos, -1);
        typedArray.recycle();
        if (layoutRes == -1) {
            return R.layout.item_card;
        }
        return layoutRes;
    }

    public void setArtistGridStyle(int viewAs) {
        mPreferences.edit().putInt(ARTIST_GRID_STYLE, viewAs).apply();
    }

    public boolean toggleSeparateLine() {
        return mPreferences.getBoolean(TOGGLE_SEPARATE_LINE, false);
    }

    public int getSongGridStyle() {
        return mPreferences.getInt(SONG_GRID_STYLE, R.layout.item_list);
    }

    public void setSongGridStyle(int viewAs) {
        mPreferences.edit().putInt(SONG_GRID_STYLE, viewAs).apply();
    }

    public boolean enableAnimations() {
        return mPreferences.getBoolean(TOGGLE_ANIMATIONS, false);
    }


}
