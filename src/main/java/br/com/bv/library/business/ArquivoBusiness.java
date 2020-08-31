package br.com.bv.library.business;

import static br.com.bv.library.utils.LibraryUtil.isNull;
import static br.com.bv.library.utils.LibraryUtil.isNullOrEmpty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import br.com.bv.library.business.exception.ArquivoBusinessException;
import br.com.bv.library.models.Arquivo;
import br.com.bv.library.models.Obra;
import br.com.bv.library.utils.LibraryUtil;

public class ArquivoBusiness {

	private final String LOCAL_BASE_PATH = "C:\\Desenvolvimento\\Arquivos";

	private void validateArquivo(Arquivo arquivo) throws ArquivoBusinessException {
		if (isNull(arquivo)) {
			throw new ArquivoBusinessException("Arquivo não foi preenchido.");
		}
	}

	private void validateObra(Obra obra) throws ArquivoBusinessException {
		if (isNull(obra)) {
			throw new ArquivoBusinessException("Obra não foi preenchido.");
		}
	}

	private void validateObraId(Long obraId) throws ArquivoBusinessException {
		if (isNull(obraId)) {
			throw new ArquivoBusinessException("Id da obra não foi preenchido.");
		}
	}

	private void validateNome(String nome) throws ArquivoBusinessException {
		if (isNullOrEmpty(nome) || !nome.contains(".") || nome.substring(nome.lastIndexOf(".") + 1).length() < 3) {
			throw new ArquivoBusinessException("Nome não foi preenchido.");
		}
	}

	private void validateNomeFisico(String nome) throws ArquivoBusinessException {
		if (isNullOrEmpty(nome) || !nome.contains(".") || nome.substring(nome.lastIndexOf(".") + 1).length() < 3) {
			throw new ArquivoBusinessException("Nome físico não foi preenchido.");
		}
	}

	private void validateFile(File file) throws ArquivoBusinessException {
		if (isNull(file)) {
			throw new ArquivoBusinessException("File não foi preenchido.");
		}
	}

	private void validateFileName(String name) throws ArquivoBusinessException {
		if (isNullOrEmpty(name) || !name.contains(".") || name.substring(name.lastIndexOf(".") + 1).length() < 3) {
			throw new ArquivoBusinessException(
					"Não foi possível obter o tipo de arquivo. Parâmetro fileName obrigatório.");
		}
	}

	public Arquivo findOneByObraIdAndNome(Long obraId, String nome) throws ArquivoBusinessException {
		validateObraId(obraId);
		validateNome(nome);
		Arquivo arquivo = new Arquivo(new Obra(obraId), nome);
		// arquivo = arquivoDao.findByObraIdAndNome(arquivo);
		arquivo.setConteudoBase64(buildConteudoBase64(arquivo));
		return arquivo;
	}

	private String buildConteudoBase64(Arquivo arquivo) throws ArquivoBusinessException {
		validateArquivo(arquivo);
		File file = getFile(arquivo);
		return encodeFileToBase64(file);
	}

	public File getFile(Arquivo arquivo) throws ArquivoBusinessException {
		validateArquivo(arquivo);
		validateNomeFisico(arquivo.getNomeFisico());

		String ano = arquivo.getNomeFisico().substring(0, 4);
		String mes = arquivo.getNomeFisico().substring(4, 6);
		String dia = arquivo.getNomeFisico().substring(6, 8);

		String[] subfolders = { ano, mes, dia };

		StringBuilder subfoldersPath = new StringBuilder();
		for (String subfolder : subfolders) {
			subfoldersPath.append(File.separator);
			subfoldersPath.append(subfolder);
		}
		subfoldersPath.append(File.separator);
		subfoldersPath.append(arquivo.getObra().getId());
		subfoldersPath.append(File.separator);
		subfoldersPath.append(arquivo.getNomeFisico());

		File file = null;

		if (!isNullOrEmpty(LOCAL_BASE_PATH)) {
			file = new File(LOCAL_BASE_PATH.replace("\\", File.separator) + subfoldersPath.toString());
		}

		if (!file.exists()) {
			throw new ArquivoBusinessException("Arquivo não existe.");
		}

		return file;
	}

	public String encodeFileToBase64(File file) throws ArquivoBusinessException {
		try {
			validateFile(file);
			validateFileName(file.getName());

			StringBuffer sb = new StringBuffer();

			byte[] conteudoBase64 = LibraryUtil.readBytesFromFile(file.getAbsolutePath());
			conteudoBase64 = Base64.getEncoder().encode(conteudoBase64);

			sb.append("data:");
			sb.append(getEncodeType(file.getName()));
			sb.append(";");
			sb.append("base64");
			sb.append(",");
			sb.append(new String(conteudoBase64));

			return sb.toString();

		} catch (IOException e) {
			throw new ArquivoBusinessException("Não foi possível realizar a condificação do conteúdo do arquivo.");
		} catch (ArquivoBusinessException e) {
			throw new ArquivoBusinessException("Não foi possível realizar a condificação do conteúdo do arquivo.", e);
		}
	}

	private String getEncodeType(String fileName) throws ArquivoBusinessException {
		try {
			validateFileName(fileName);
		} catch (ArquivoBusinessException e) {
			throw new ArquivoBusinessException("Não foi possível obter o tipo de encode.");
		}

		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
		if ("BMP".equals(fileExtension))
			return "image/bmp";
		if ("JPEG".equals(fileExtension) || "JPG".equals(fileExtension))
			return "image/jpeg";
		if ("GIF".equals(fileExtension))
			return "image/gif";
		if ("PNG".equals(fileExtension))
			return "image/png";
		if ("PDF".equals(fileExtension))
			return "application/pdf";
		if ("XLS".equals(fileExtension))
			return "application/vnd.ms-excel";
		if ("XLSX".equals(fileExtension))
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		if ("TXT".equals(fileExtension))
			return "text/plain";
		if ("DOC".equals(fileExtension))
			return "application/msword";
		if ("DOCX".equals(fileExtension))
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		if ("TIFF".equals(fileExtension))
			return "image/tiff";
		else
			throw new ArquivoBusinessException("Não foi possível obter o tipo de encode. Extensão não suportada.");
	}

	public Arquivo save(Arquivo arquivo) throws ArquivoBusinessException {
		try {
			validateArquivo(arquivo);
			validateObra(arquivo.getObra());
			validateObraId(arquivo.getObra().getId());
			validateNome(arquivo.getNome());

			saveFile(arquivo);
			arquivo.setConteudoBase64(null);
			// arquivo = arquivoDao.save(arquivo);
			return arquivo;

		} catch (ArquivoBusinessException e) {
			throw new ArquivoBusinessException("Não foi possível salvar arquivo.", e);
		}
	}

	public void saveFile(Arquivo arquivo) throws ArquivoBusinessException {
		try {
			validateArquivo(arquivo);
			validateNome(arquivo.getNome());

			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			arquivo.setNomeFisico(sdf.format(c) + arquivo.getNome().lastIndexOf("."));

			String ano = arquivo.getNomeFisico().substring(0, 4);
			String mes = arquivo.getNomeFisico().substring(4, 6);
			String dia = arquivo.getNomeFisico().substring(6, 8);

			Path path = Paths.get(LOCAL_BASE_PATH + File.separator + ano + File.separator + mes + File.separator + dia
					+ File.separator + arquivo.getNomeFisico());

			byte[] bFile = decodeFileToBase64(arquivo);

			Files.write(path, bFile);

		} catch (IOException e) {
			throw new ArquivoBusinessException("Não foi possível salvar arquivo físico.");

		} catch (ArquivoBusinessException e) {
			throw new ArquivoBusinessException("Não foi possível salvar arquivo físico.", e);
		}
	}

	private byte[] decodeFileToBase64(Arquivo arquivo) {
		String conteudoBase64 = arquivo.getConteudoBase64();
		conteudoBase64 = conteudoBase64.substring(conteudoBase64.lastIndexOf("base64,") + 1);
		return Base64.getDecoder().decode(conteudoBase64);
	}

}
