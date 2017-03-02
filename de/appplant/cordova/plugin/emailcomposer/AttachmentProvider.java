package de.appplant.cordova.plugin.emailcomposer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v4.widget.CursorAdapter;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;

public class AttachmentProvider extends ContentProvider {
    public static final String AUTHORITY = ".plugin.emailcomposer.attachmentprovider";
    private UriMatcher uriMatcher;

    public boolean onCreate() {
        String pkgName = getContext().getPackageName();
        this.uriMatcher = new UriMatcher(-1);
        this.uriMatcher.addURI(new StringBuilder(String.valueOf(pkgName)).append(AUTHORITY).toString(), "*", 1);
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        switch (this.uriMatcher.match(uri)) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                return ParcelFileDescriptor.open(new File(new StringBuilder(String.valueOf(getContext().getCacheDir() + EmailComposer.STORAGE_FOLDER)).append(File.separator).append(uri.getLastPathSegment()).toString()), 268435456);
            default:
                throw new FileNotFoundException("Unsupported uri: " + uri.toString());
        }
    }

    public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
        return 0;
    }

    public int delete(Uri arg0, String arg1, String[] arg2) {
        return 0;
    }

    public Uri insert(Uri arg0, ContentValues arg1) {
        return null;
    }

    public String getType(Uri arg0) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(arg0.getPath()));
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor result = new MatrixCursor(projection);
        Object[] row = new Object[projection.length];
        long fileSize = new File(getContext().getCacheDir() + File.separator + uri.getLastPathSegment()).length();
        for (int i = 0; i < projection.length; i++) {
            if (projection[i].compareToIgnoreCase("_display_name") == 0) {
                row[i] = uri.getLastPathSegment();
            } else if (projection[i].compareToIgnoreCase("_size") == 0) {
                row[i] = Long.valueOf(fileSize);
            }
        }
        result.addRow(row);
        return result;
    }
}
