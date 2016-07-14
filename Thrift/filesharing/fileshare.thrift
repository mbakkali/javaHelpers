namespace java fileshare.rpc

struct Fichier{
  1: string nom;
  2: binary content;
}

service Rpc {
  Fichier downloadFile(1: string filename);
  void uploadFile(1: Fichier fichier);
  void deleteFile(1: string filename);
  string listFichier();
}
