package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import main.Constants;

// TODO use this class
public class IO implements Constants { // NO_UCD (unused code)
	private static final int BUFFER_SIZE=1024;
	
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;
	private boolean fileSelected = false;
	// Restricted Files List
	private final List<File> restrictedFilesList = new ArrayList<File>();

	public IO() {
		// Adding Restricted Files
		restrictedFilesList.add(new File(OUTPUT_PATH + "/README_File_Paths.txt"));
		restrictedFilesList.add(new File(OUTPUT_PATH + "/crash_tracking.txt"));
	}

	public void createNewFile(String name) {
		File newFile = new File(OUTPUT_PATH + "/" + name + ".txt");
		if (newFile != null && !newFile.exists())
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			System.out.println("This file already exists so a new" + " one will not be created.");
	}

	public void deleteFile(String path) {
		File file = new File(path);
		System.out.println(file.getName());
		System.out.println(file.exists());
		if (file != null && file.exists())
			file.delete();
		System.out.println(file.exists());
	}

	public void writeLine(String line) {
		try {
			if (bw != null) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readLine() {
		String line = "";
		try {
			if (br != null)
				line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	// Returns the number of lines within the file located at filePath.
	public int countLines() throws IOException {
		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			byte[] c = new byte[BUFFER_SIZE];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {	
				empty = false;
				for (int i = 0; i < readChars; ++i)
					if (c[i] == '\n')
						++count;
			}
			return (count == 0 && !empty) ? 1 : count;
		}
	}

	// Returns the specified line of the file located at filePath as a string.
	public String getLine(int lineNum) {
		String chosenLine = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<>();
			String line;
			int i = 0;
			while (i < 5) { // Leave some empty lines at the end of the list which may be utilized as place-holders
				line = br.readLine();
				if (line == null) {
					i++;
					lines.add("");
					continue;
				}
				lines.add(line);
			}

			chosenLine = lines.get(lineNum - 1); // First line is # 1, but first item in array is #0
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chosenLine;
	}

	// Removes the Nth line located within the file at filePath.
	public void deleteNthLine(int NthLineNum) {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
	
			// Leave the n first lines unchanged.
			for (int i = 1; i < NthLineNum; i++)
				raf.readLine();
	
			// Shift remaining lines upwards.
			long writePos = raf.getFilePointer();
			raf.readLine();
			long readPos = raf.getFilePointer();
	
			byte[] buf = new byte[1024];
			int n;
			while (-1 != (n = raf.read(buf))) {
				raf.seek(writePos);
				raf.write(buf, 0, n);
				readPos += n;
				writePos += n;
				raf.seek(readPos);
			}
	
			raf.setLength(writePos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void trim() {
		int firstNonZeroValueLine = 0;
		try {
			for (int i = 1; i < countLines(); i++) {
				String[] splitLine = getLine(i).split(",");

				double leftDriveVoltage = Double.parseDouble(splitLine[0]);
				double rightDriveVoltage = Double.parseDouble(splitLine[1]);
				double leftIntakeWheelValue = Double.parseDouble(splitLine[2]);
				double rightIntakeWheelValue = Double.parseDouble(splitLine[3]);

				boolean a = Boolean.parseBoolean(splitLine[4]);
				boolean b = Boolean.parseBoolean(splitLine[5]);
				boolean x = Boolean.parseBoolean(splitLine[6]);
				boolean y = Boolean.parseBoolean(splitLine[7]);
				boolean leftBumper = Boolean.parseBoolean(splitLine[8]);
				boolean rightBumper = Boolean.parseBoolean(splitLine[9]);
				boolean select = Boolean.parseBoolean(splitLine[10]);
				boolean start = Boolean.parseBoolean(splitLine[11]);
				boolean leftJoystickPress = Boolean.parseBoolean(splitLine[12]);
				boolean rightJoystickPress = Boolean.parseBoolean(splitLine[13]);
				boolean leftTrigger = Boolean.parseBoolean(splitLine[14]);
				boolean rightTrigger = Boolean.parseBoolean(splitLine[15]);

				boolean a2 = Boolean.parseBoolean(splitLine[16]);
				boolean b2 = Boolean.parseBoolean(splitLine[17]);
				boolean x2 = Boolean.parseBoolean(splitLine[18]);
				boolean y2 = Boolean.parseBoolean(splitLine[19]);
				boolean leftBumper2 = Boolean.parseBoolean(splitLine[20]);
				boolean rightBumper2 = Boolean.parseBoolean(splitLine[21]);
				boolean select2 = Boolean.parseBoolean(splitLine[22]);
				boolean start2 = Boolean.parseBoolean(splitLine[23]);
				boolean leftJoystickPress2 = Boolean.parseBoolean(splitLine[24]);
				boolean rightJoystickPress2 = Boolean.parseBoolean(splitLine[25]);
				boolean leftTrigger2 = Boolean.parseBoolean(splitLine[26]);
				boolean rightTrigger2 = Boolean.parseBoolean(splitLine[27]);

				if (leftDriveVoltage != 0 || rightDriveVoltage != 0 || leftIntakeWheelValue != 0
						|| rightIntakeWheelValue != 0 || a || b || x || y || leftBumper || rightBumper || select
						|| start || leftJoystickPress || rightJoystickPress || leftTrigger || rightTrigger || a2 || b2
						|| x2 || y2 || leftBumper2 || rightBumper2 || select2 || start2 || leftJoystickPress2
						|| rightJoystickPress2 || leftTrigger2 || rightTrigger2) {

					firstNonZeroValueLine = i;
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i < firstNonZeroValueLine; i++) deleteNthLine(1);
	}

	public void changePath(String nameOrPath, boolean useFileLookup) {
		// If useFileLookup is true then it will search for the specified
		// fileName and get its path.
		// ---------------------useFileLookup currently unused
		// ---------------------Only pass this method (path, false) or (name, true)
		if (useFileLookup) {
			if (nameOrPath == "00000000000000000000000000000000000000000000000000000000000")
				fileSelected = false;
			else
				for (File file : new File(OUTPUT_PATH).listFiles())
					if (file.getName().equals(nameOrPath))
						changePath(file.getPath(), false);
		} else {
			file = new File(nameOrPath);
			fileSelected = true;
		}
	}

	public void resetForRead() {
		if (file != null) {
			try {
				fr = new FileReader(file);
				if (fr != null)
					br = new BufferedReader(fr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void resetForWrite() {
		if (file != null) {
			try {
				fw = new FileWriter(file);
				if (fw != null)
					bw = new BufferedWriter(fw);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public File[] getFiles(String path) {
		List<File> textFiles = new ArrayList<File>();
		File dir = new File(path + "/");
		for (File file : dir.listFiles()) {
			if (file.getName().toLowerCase().endsWith((".txt")) && !restrictedFilesList.contains(file)) {
				textFiles.add(file);
			}
		}

		File[] allFiles = new File[textFiles.size()];
		for (int i = 0; i < textFiles.size(); i++)
			allFiles[i] = textFiles.get(i);

		return allFiles;
	}

	public String getWorkingFile() {
		if (file != null && fileSelected)
			return file.getName();
		else
			return "No File Selected";
	}
}