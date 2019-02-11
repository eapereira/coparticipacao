package br.com.spread.qualicorp.wso2.coparticipacao.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.spread.qualicorp.wso2.coparticipacao.dao.DaoException;
import br.com.spread.qualicorp.wso2.coparticipacao.exception.CoParticipacaoException;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public abstract class XmlUtils {

	private static final Logger LOGGER = LogManager.getLogger(XmlUtils.class);

	public static <T> void writeFile(String filePath, T xml, Class<T> subClass) throws CoParticipacaoException {
		JAXBContext jaxbContext;
		Marshaller jaxbMarshaller;
		FileOutputStream fileOutputStream = null;
		File file;

		try {
			LOGGER.info("BEGIN");

			jaxbContext = JAXBContext.newInstance(subClass);

			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			file = new File(filePath);

			if (file.exists()) {
				file.delete();
			}

			fileOutputStream = new FileOutputStream(file);
			jaxbMarshaller.marshal(xml, fileOutputStream);

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			close(fileOutputStream);
		}
	}

	private static void close(FileOutputStream fileOutputStream) throws CoParticipacaoException {
		try {
			LOGGER.info("BEGIN");

			if (fileOutputStream != null) {
				fileOutputStream.close();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	private static void close(FileInputStream fileInputStream) throws CoParticipacaoException {
		try {
			LOGGER.info("BEGIN");

			if (fileInputStream != null) {
				fileInputStream.close();
			}

			LOGGER.info("END");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	public static <T> T readFile(File file, Class<T> subClass) throws CoParticipacaoException {
		JAXBContext jaxbContext;
		Unmarshaller jaxbUnmarshaller;
		FileInputStream fileInputStream = null;
		T xml = null;

		try {
			LOGGER.info("BEGIN");

			jaxbContext = JAXBContext.newInstance(subClass);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			if (file.exists()) {
				LOGGER.info("Unmarshalling XML file[{}]:", file.getName());

				fileInputStream = new FileInputStream(file);

				if (fileInputStream != null) {
					xml = (T) jaxbUnmarshaller.unmarshal(fileInputStream);
				}
			}

			LOGGER.info("END");
			return xml;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			close(fileInputStream);
		}
	}

	public static <T> T readFile(String filePath, Class<T> subClass) throws CoParticipacaoException {
		T data;

		try {
			LOGGER.info("BEGIN");

			data = readFile(new File(filePath), subClass);

			LOGGER.info("END");
			return data;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}
}
