package com.api.ce.crud;

import java.util.Iterator;
import java.util.Map;

import com.api.ce.pojo.DatosContenidoDocumento;
import com.api.ce.pojo.DatosCreacionObjeto;
import com.api.ce.utils.FiltrosPropiedad;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.constants.ReservationType;
import com.filenet.api.core.ContentElement;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.property.PropertyFilter;
import com.filenet.api.util.Id;

public class CrudDocumento {

	private final FiltrosPropiedad filtrosPropiedad = new FiltrosPropiedad();

	@SuppressWarnings("unchecked")
	public String crearDocumento(ObjectStore os, DatosCreacionObjeto datosCreacionObjeto,
			DatosContenidoDocumento datosContenido) {

		// creacion objeto
		Document doc = Factory.Document.createInstance(os, datosCreacionObjeto.getClaseDocumental());

		// set propiedades
		datosCreacionObjeto.getValores().entrySet()
				.forEach(entry -> doc.getProperties().putObjectValue(entry.getKey(), entry.getValue()));

		// contenido
		ContentElementList contentList = Factory.ContentElement.createList();
		ContentTransfer content = Factory.ContentTransfer.createInstance();
		content.set_RetrievalName(datosContenido.getNombreArchivo());
		content.set_ContentType(datosContenido.getContentType());
		content.setCaptureSource(datosContenido.getStream());
		contentList.add(content);
		doc.set_ContentElements(contentList);

		// checkin
		CheckinType checkinType = datosContenido.isVersionMayor() ? CheckinType.MAJOR_VERSION
				: CheckinType.MINOR_VERSION;
		doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, checkinType);

		// commit
		doc.save(RefreshMode.NO_REFRESH);

		// referencia folder padre
		Folder folderPadre = Factory.Folder.fetchInstance(os, datosCreacionObjeto.getIdFolderPadre(), null);
		ReferentialContainmentRelationship rel = folderPadre.file(doc, AutoUniqueName.NOT_AUTO_UNIQUE,
				datosContenido.getNombreArchivo(), DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
		rel.save(RefreshMode.NO_REFRESH);

		// commit
		doc.save(RefreshMode.REFRESH, filtrosPropiedad.obtenerFiltroxId());
		return doc.get_Id().toString();
	}

	@SuppressWarnings("unchecked")
	public String versionarDocumento(ObjectStore os, String idDocumento, DatosContenidoDocumento datosContenido) {

		// recuperacion objeto
		Document docVersionamiento = Factory.Document.fetchInstance(os, new Id(idDocumento), null);

		// checkout
		docVersionamiento.checkout(ReservationType.COLLABORATIVE, null, null, null);
		docVersionamiento.save(RefreshMode.REFRESH);

		// contenido
		ContentElementList contentList = Factory.ContentElement.createList();
		ContentTransfer content = Factory.ContentTransfer.createInstance();
		content.setCaptureSource(datosContenido.getStream());
		content.set_RetrievalName(datosContenido.getNombreArchivo());
		content.set_ContentType(datosContenido.getContentType());
		contentList.add(content);

		// checkin
		Document reservation = (Document) docVersionamiento.get_Reservation();
		reservation.set_ContentElements(contentList);
		reservation.getProperties().putValue("DocumentTitle", datosContenido.getNombreArchivo());
		CheckinType checkinType = datosContenido.isVersionMayor() ? CheckinType.MAJOR_VERSION
				: CheckinType.MINOR_VERSION;
		reservation.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, checkinType);

		// commit
		reservation.save(RefreshMode.REFRESH, filtrosPropiedad.obtenerFiltroxId());
		return reservation.get_Id().toString();
	}

	@SuppressWarnings("unchecked")
	public DatosContenidoDocumento extraerContenido(ObjectStore os, String idDocumento) {

		DatosContenidoDocumento datos = new DatosContenidoDocumento();

		// recuperacion objeto
		PropertyFilter pf = this.filtrosPropiedad.obtenerFiltroContenido();
		Document doc = Factory.Document.fetchInstance(os, new Id(idDocumento), pf);
		doc = (Document) doc.get_VersionSeries().get_CurrentVersion();

		// contenido
		ContentElementList docContentList = doc.get_ContentElements();
		Iterator<ContentElement> iter = docContentList.iterator();
		if (iter.hasNext()) {
			ContentTransfer ct = (ContentTransfer) iter.next();
			datos.setContentType(ct.get_ContentType());
			datos.setNombreArchivo(ct.get_RetrievalName());
			datos.setStream(ct.accessContentStream());
		}

		return datos;
	}

	public int actualizarPropiedadesDocumento(ObjectStore os, String idDocumento, Map<String, Object> valores) {

		// recuperacion objeto
		PropertyFilter pf = this.filtrosPropiedad.obtenerFiltroxColeccion(valores.keySet());
		Document doc = Factory.Document.fetchInstance(os, new Id(idDocumento), pf);

		// set propiedades
		valores.entrySet().forEach(entry -> doc.getProperties().putObjectValue(entry.getKey(), entry.getValue()));

		// commit
		doc.save(RefreshMode.NO_REFRESH);
		return valores.size();
	}

	public boolean borrarDocumento(ObjectStore os, String claseDocumental, String idDocumento) {

		// recuperacion objeto
		Document doc = Factory.Document.getInstance(os, claseDocumental, new Id(idDocumento));

		// delete
		doc.delete();

		// commit
		doc.save(RefreshMode.NO_REFRESH);
		return Boolean.TRUE;
	}
	
	public boolean referenciarDocumento(ObjectStore os, String idFolderaReferenciar, String idDocumento) {

		// recuperacion objeto
		Document doc = Factory.Document.fetchInstance(os, new Id(idDocumento), null);
		Folder folderPadre = Factory.Folder.fetchInstance(os, new Id(idFolderaReferenciar), null);
		ReferentialContainmentRelationship rel = folderPadre.file(doc, AutoUniqueName.NOT_AUTO_UNIQUE,
				doc.get_Name(), DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
		rel.save(RefreshMode.NO_REFRESH);
		return Boolean.TRUE;
	}

}
