package jp.ac.ehime_u.cite.sasaki.easyguide.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import android.util.Log;

/**
 * @author Takashi SASAKI {@link "http://twitter.com/TakashiSasaki"}
 * 
 */
public class ZipInflator {

	private InputStream inputStream;
	private File destinationDirectory;
	private final int bufferSize = 65536;

	@SuppressWarnings("serial")
	static class Exception extends RuntimeException {
		public Exception(String message) {
			super(message);
		}
	}

	/**
	 * @param zip_file
	 * @param domain_directory
	 * @throws FileNotFoundException
	 */
	public ZipInflator(File zip_file, File domain_directory)
			throws FileNotFoundException {
		this.inputStream = new FileInputStream(zip_file);
		this.destinationDirectory = domain_directory;
	}

	/**
	 * @param input_stream
	 * @param domain_
	 */
	public ZipInflator(InputStream input_stream, Domain domain_) {
		this.inputStream = input_stream;
		this.destinationDirectory = domain_.getDomainDirectory();
	}

	/**
	 * 
	 */
	public void Inflate() {
		ZipInputStream zip_input_stream = new ZipInputStream(inputStream);
		Log.v(this.getClass().getSimpleName(),
				"Inflating ZIP entries from ZIP input stream.");
		while (true) {
			ZipEntry zip_entry;
			try {
				zip_entry = zip_input_stream.getNextEntry();
			} catch (IOException e1) {
				Log.v(this.getClass().getSimpleName(),
						"Can't find next ZIP entry. " + e1.getMessage());
				return;
			}
			if (zip_entry == null) {
				Log.v(this.getClass().getSimpleName(),
						"No more entry in ZIP input stream.");
				return;
			}

			Log.v(this.getClass().getSimpleName(), "Inflating ZIP entry "
					+ zip_entry.getName() + ", size " + zip_entry.getSize());
			File destination_file = new File(this.destinationDirectory,
					zip_entry.getName());
			if (zip_entry.isDirectory()) {
				destination_file.mkdirs();
				continue;
			}
			// BufferedInputStream buffered_input_stream;
			// buffered_input_stream = new
			// BufferedInputStream(zip_input_stream);
			if (!destination_file.getParentFile().exists()) {
				destination_file.getParentFile().mkdirs();
			}
			BufferedOutputStream buffered_output_stream;
			try {
				buffered_output_stream = new BufferedOutputStream(
						new FileOutputStream(destination_file));
			} catch (FileNotFoundException e) {
				throw new Exception("Unable to get output stream of "
						+ destination_file.getAbsolutePath() + ". "
						+ e.getMessage());
			}
			try {
				while (zip_input_stream.available() > 0) {
					Log.v(this.getClass().getSimpleName(),
							zip_input_stream.available()
									+ " bytes remaining to be read.");
					byte[] buffer = new byte[this.bufferSize];
					Log.v(this.getClass().getSimpleName(),
							"starting to read some data from ZIP entry "
									+ zip_entry.getName());
					int bytes_read = zip_input_stream.read(buffer, 0,
							this.bufferSize);
					if (bytes_read < 0) {
						Log.v(this.getClass().getSimpleName(),
								"reached to the end of file of "
										+ zip_entry.getName());
						break;
					}
					Log.v(this.getClass().getSimpleName(),
							"finished to read " + bytes_read
									+ " bytes from ZIP entry "
									+ zip_entry.getName());
					Log.v(this.getClass().getSimpleName(), "starting to write "
							+ bytes_read + " to " + destination_file.getName());
					buffered_output_stream.write(buffer, 0, bytes_read);
					Log.v(this.getClass().getSimpleName(), "finished to write "
							+ bytes_read + " to " + destination_file.getName());
				}
			} catch (IOException e) {
				throw new Exception(
						"Unable to copy data from an ZIP entry to a file. "
								+ e.getMessage());
			}// while
			try {
				zip_input_stream.closeEntry();
				// buffered_input_stream.close();
			} catch (IOException e) {
				throw new Exception("Failed to close input stream. "
						+ e.getMessage());
			}
			try {
				buffered_output_stream.close();
			} catch (IOException e) {
				throw new Exception("Failed to close output stream. "
						+ e.getMessage());
			}
			zip_entry.clone();
		}// while

	}

	/**
	 * @param file
	 * 
	 */
	@Deprecated
	public void Inflate(File file) {
		ZipFile zip_file;
		try {
			zip_file = new ZipFile(file);
		} catch (ZipException e) {
			throw new Exception("Unable to open " + file.getAbsolutePath()
					+ " as a ZIP file. " + e.getMessage());
		} catch (IOException e) {
			throw new Exception("Unable to open " + file.getAbsolutePath()
					+ " as a ZIP file. " + e.getMessage());
		}
		Enumeration<? extends ZipEntry> zip_entries = zip_file.entries();
		while (zip_entries.hasMoreElements()) {
			ZipEntry zip_entry = zip_entries.nextElement();
			File destination_file = new File(this.destinationDirectory,
					zip_entry.getName());
			if (zip_entry.isDirectory()) {
				destination_file.mkdirs();
				continue;
			}
			BufferedInputStream buffered_input_stream;
			try {
				buffered_input_stream = new BufferedInputStream(
						zip_file.getInputStream(zip_entry));
			} catch (IOException e) {
				throw new Exception("Unable to get an entry "
						+ zip_entry.getName() + " in the ZIP file. "
						+ e.getMessage());
			}
			if (!destination_file.getParentFile().exists()) {
				destination_file.getParentFile().mkdirs();
			}
			BufferedOutputStream buffered_output_stream;
			try {
				buffered_output_stream = new BufferedOutputStream(
						new FileOutputStream(destination_file));
			} catch (FileNotFoundException e) {
				throw new Exception("Unable to get output stream of "
						+ destination_file.getAbsolutePath() + ". "
						+ e.getMessage());
			}
			try {
				while (buffered_input_stream.available() > 0) {
					byte[] buffer = new byte[this.bufferSize];
					buffered_input_stream.read(buffer);
					buffered_output_stream.write(buffer);
				}
			} catch (IOException e) {
				throw new Exception(
						"Unable to copy data from an ZIP entry to a file. "
								+ e.getMessage());
			}// while
			try {
				buffered_input_stream.close();
			} catch (IOException e) {
				throw new Exception("Failed to close input stream. "
						+ e.getMessage());
			}
			try {
				buffered_output_stream.close();
			} catch (IOException e) {
				throw new Exception("Failed to close output stream. "
						+ e.getMessage());
			}
		}// while
	}// UnZip()
}