package io.github.alancavalcante_dev.codefreelaapi.service;


import io.github.alancavalcante_dev.codefreelaapi.dto.StateBusiness;
import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectInsertDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProject;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProjectProfile;
import io.github.alancavalcante_dev.codefreelaapi.model.Profile;
import io.github.alancavalcante_dev.codefreelaapi.repository.BusinessProjectProfileRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.BusinessProjectRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileRepository;
import io.github.alancavalcante_dev.codefreelaapi.validate.BusinessProjectValidate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BusinessProjectService {

    @Autowired
    private BusinessProjectRepository bussinesProjectRepository;

    @Autowired
    private BusinessProjectValidate businessProjectValidate;

    @Autowired
    private ProfileRepository profileRepository;


    public List<BusinessProject> getAllBusinessProject() {
        return this.bussinesProjectRepository.findAll();
    }

    public Optional<BusinessProject> findyByIdBusinessProject(UUID uuid) {
        return bussinesProjectRepository.findById(uuid);
    }


    @Transactional
    public BusinessProject save(BusinessProjectInsertDTO request) throws Exception {
        Optional<Profile> client = profileRepository.findById(UUID.fromString(request.getIdProfile()));
        if (client.isEmpty()) { throw new Exception("Perfil não encontrado");}

        BusinessProject project = new BusinessProject();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTags(request.getTags());
        project.setPriceDay(request.getPriceDay());
        project.setPriceHour(request.getPriceHour());
        project.setPriceProject(request.getPriceProject());
        project.setClosingDate(request.getClosingDate());
        project.setStateBusiness(StateBusiness.Open);

        businessProjectValidate.save(project);

        BusinessProject projectSave = bussinesProjectRepository.save(project);

        BusinessProjectProfile profile = new BusinessProjectProfile();
        profile.setBusinessProject(projectSave);
        profile.setProfile(client.get());
        projectSave.getProfiles().add(profile);

        return bussinesProjectRepository.save(project);

    }

    public BusinessProject update(BusinessProject project) {
        return bussinesProjectRepository.save(project);
    }

    public void delete(BusinessProject project) {
        bussinesProjectRepository.delete(project);
    }
}
