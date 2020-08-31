package br.com.bv.library.models;

/**
 * NOME_ARQUIVO VARCHAR2(255) COD_HASH_MD5 VARCHAR2(32) VERSAO NUMBER(16)
 * NOME_ARQUIVO_FISICO VARCHAR2(255) COD_USUARIO VARCHAR2(15) NUM_SESSAO
 * NUMBER(8) DATA_INSERCAO DATE DATA_INSERCAO_REG DATE
 */
// nomeArquivo: string;
//codHashMD5?: string;
//nomeArquivoFisico?: string;
//conteudoCodificado?: string;
//arrayBuffer?: ArrayBuffer;
//fileType?: string;
//erroNoArquivo?: boolean;
public class Arquivo {

	private Long id;
	private Obra obra;
	private String nome;
	private String nomeFisico;
	private String hashMD5;
	private String conteudoBase64;

	public Arquivo() {
	}

	public Arquivo(Long id, Obra obra, String nome, String nomeFisico, String hashMD5, String conteudoBase64) {
		this.id = id;
		this.obra = obra;
		this.nome = nome;
		this.nomeFisico = nomeFisico;
		this.hashMD5 = hashMD5;
		this.conteudoBase64 = conteudoBase64;
	}

	public Arquivo(Obra obra, String nome) {
		this.obra = obra;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFisico() {
		return nomeFisico;
	}

	public void setNomeFisico(String nomeFisico) {
		this.nomeFisico = nomeFisico;
	}

	public String getHashMD5() {
		return hashMD5;
	}

	public void setHashMD5(String hashMD5) {
		this.hashMD5 = hashMD5;
	}

	public String getConteudoBase64() {
		return conteudoBase64;
	}

	public void setConteudoBase64(String conteudoBase64) {
		this.conteudoBase64 = conteudoBase64;
	}

	@Override
	public String toString() {
		return "Arquivo [id=" + id + ", nome=" + nome + ", nomeFisico=" + nomeFisico + ", hashMD5=" + hashMD5 + ", obra=" + obra + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

//	  public void generateStreamFile(ByteArrayInputStream in, String pNomeArquivo) throws IOException {
//	      
//		      FacesContext context = FacesContext.getCurrentInstance();
//		      HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//		      ServletOutputStream out = null;
//		      ReadableByteChannel src = null;
//		      WritableByteChannel dst = null;
//		      try {
//		          out = response.getOutputStream();
//		          response.setContentType("application/octet-stream");
//	  response.setHeader("Content-Disposition","attachment;filename="+ pNomeArquivo +";");
//	  response.setHeader("Content-Type", "application/force-download;");
//	  response.setHeader("Content-Type", "application/download;");
//	  response.setHeader("Content-Transfer-Encoding", "binary;");
//	  response.setHeader("Pragma", " ");
//	  response.setHeader("Cache-Control", " ");
//	  response.setHeader("Pragma", "no-cache;");
//	  response.setDateHeader("Expires", new java.util.Date().getTime());
//	      src = Channels.newChannel(in);
//	      dst = Channels.newChannel(out);
//	      fastChannelCopy(src, dst);
//	      out.flush();
//	      context.responseComplete();
//	  } catch (IOException e) {
//	      throw new IOException("Erro na Geração do Arquivo.", e);
//	      } finally {
//	      	SabiusUtil.close(in);
//	      	SabiusUtil.close(out);
//	      	SabiusUtil.close(src);
//	      	SabiusUtil.close(dst);
//	      }
//	  }

}
