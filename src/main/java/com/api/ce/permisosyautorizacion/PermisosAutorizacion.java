package com.api.ce.permisosyautorizacion;

import com.filenet.api.collection.AccessPermissionList;
import com.filenet.api.constants.AccessLevel;
import com.filenet.api.constants.AccessType;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.security.AccessPermission;
import com.filenet.api.util.Id;

@SuppressWarnings("deprecation")
public class PermisosAutorizacion {

	@SuppressWarnings({"unchecked" })
	public boolean asignarPermisosFullControlUsuariosAutenticados(ObjectStore os, String idDocument) {

		Document doc = Factory.Document.fetchInstance(os, new Id(idDocument), null);

		// Create a new permissions list.
		AccessPermissionList apl = Factory.AccessPermission.createList();

		// create a new access permission object
		AccessPermission ap = Factory.AccessPermission.createInstance();

		ap.set_GranteeName("#AUTHENTICATED-USERS");
		ap.set_AccessType(AccessType.ALLOW);
		ap.set_AccessMask(AccessLevel.FULL_CONTROL_ANNOTATION_AS_INT);

		// Add the permissions to the list.
		apl.add(ap);

		// set apl
		doc.set_Permissions(apl);

		doc.save(RefreshMode.NO_REFRESH);

		return true;
	}

}
