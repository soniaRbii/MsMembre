package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.EvenementBean;
import com.example.demo.beans.OutilBean;
import com.example.demo.beans.PublicationBean;
import com.example.demo.dao.EnseignantRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.dao.Membreeventrepository;
import com.example.demo.dao.Membreoutilrepository;
import com.example.demo.dao.Membrepubrepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Member;
import com.example.demo.entities.Membre_Evenement;
import com.example.demo.entities.Membre_Events_Ids;
import com.example.demo.entities.Membre_Outil;
import com.example.demo.entities.Membre_Outils_Ids;
import com.example.demo.entities.Membre_Pub_Ids;
import com.example.demo.entities.Membre_Publication;
import com.example.demo.proxies.EvenementProxyService;
import com.example.demo.proxies.OutilProxyService;
import com.example.demo.proxies.PublicationProxyService;

@Service
public class MembreServiceImpl implements IMemberService {

	@Autowired
	MembreRepository membreRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantRepository enseignantRepository;
	@Autowired
	Membreeventrepository membreeventrepository;
	@Autowired
	Membreoutilrepository membreoutilrepository;
	@Autowired
	Membrepubrepository membrepubrepository;
	@Autowired
	PublicationProxyService publicationProxyService;
	@Autowired
	OutilProxyService outilProxyService;
	@Autowired
	EvenementProxyService evenementProxyService;

	public Member addMember(Member m) {
		return membreRepository.save(m);
	}

	public void deleteMember(Long id) {
		if (membreRepository.findById(id).isPresent())
			membreRepository.deleteById(id);
	}

	public Member updateMember(Member p) {
		return membreRepository.saveAndFlush(p);
	}

	public Member findMember(Long id) {
		return membreRepository.findById(id).get();
	}

	public List<Member> findAll() {
		return membreRepository.findAll();
	}

	public Member findByCin(String cin) {

		return membreRepository.findByCin(cin);
	}

	public Member findByEmail(String email) {

		return membreRepository.findByEmail(email);
	}

	public List<Member> findByNom(String nom) {

		return membreRepository.findByNomStartingWith(nom);
	}

	@Override
	public List<Etudiant> findByDiplome(String diplome) {

		return etudiantRepository.findByDiplome(diplome);
	}

	@Override
	public List<EnseignantChercheur> findByGrade(String grade) {

		return enseignantRepository.findByGrade(grade);
	}

	@Override
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {

		return enseignantRepository.findByEtablissement(etablissement);
	}

	@Override
	public Etudiant affecterencadrantToetudiant(Long idetd, Long idens) {
		Etudiant etd = etudiantRepository.findById(idetd).get();
		EnseignantChercheur ens = enseignantRepository.findById(idens).get();
		etd.setEncadrant(ens);
		return etudiantRepository.save(etd);
	}

	@Override
	public List<Etudiant> findEtudiantsByEncadrant(Long idens) {

		EnseignantChercheur ens = enseignantRepository.findById(idens).get();

		return etudiantRepository.findByEncadrant(ens);
	}

	@Override
	public void affecterauteurTopublication(Long idauteur, Long idpub) {
		Member mbr = membreRepository.findById(idauteur).get();
		Membre_Publication mbs = new Membre_Publication();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Pub_Ids(idpub, idauteur));
		membrepubrepository.save(mbs);

	}

	public List<PublicationBean> findPublicationparauteur(Long idauteur) {
		List<PublicationBean> pubs = new ArrayList<PublicationBean>();
		List<Membre_Publication> idpubs = membrepubrepository.findpubId(idauteur);

		idpubs.forEach(s ->

		pubs.add(publicationProxyService.findPublicationById(s.getId().getPublication_id())));

		return pubs;
	}

	@Override
	public void affecterauteurToEvenement(Long idauteur, Long idevent) {
		Member mbr = membreRepository.findById(idauteur).get();
		Membre_Evenement mbs = new Membre_Evenement();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Events_Ids(idevent, idauteur));
		membreeventrepository.save(mbs);

	}

	@Override
	public void affecterauteurToOutil(Long idauteur, Long idoutil) {
		Member mbr = membreRepository.findById(idauteur).get();
		Membre_Outil mbs = new Membre_Outil();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Outils_Ids(idoutil, idauteur));
		membreoutilrepository.save(mbs);

	}

	public List<EvenementBean> findEvenementparauteur(Long idauteur) {
		List<EvenementBean> events = new ArrayList<EvenementBean>();
		List<Membre_Evenement> idevents = membreeventrepository.findeventId(idauteur);

		idevents.forEach(s ->

		events.add(evenementProxyService.findEvenementById(s.getId().getEvenement_id())));

		return events;
	}

	public List<OutilBean> findOutilparauteur(Long idauteur) {
		List<OutilBean> outils = new ArrayList<OutilBean>();
		List<Membre_Outil> idoutils = membreoutilrepository.findoutilId(idauteur);

		idoutils.forEach(s ->

		outils.add(outilProxyService.findOutilById(s.getId().getOutil_id())));

		return outils;
	}

}
