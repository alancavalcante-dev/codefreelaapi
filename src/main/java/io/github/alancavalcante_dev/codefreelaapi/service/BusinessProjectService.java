package io.github.alancavalcante_dev.codefreelaapi.service;


import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.StateBusiness;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProject;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProjectProfile;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import io.github.alancavalcante_dev.codefreelaapi.repository.BusinessProjectProfileRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.BusinessProjectRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileClientRepository;
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
    private BusinessProjectProfileRepository businessProjectProfileRepository;

    @Autowired
    private ProfileClientRepository clientRepository;


    public List<BusinessProject> getAllBusinessProject() {
        return this.bussinesProjectRepository.findAll();
    }

    public Optional<BusinessProject> findyByIdBusinessProject(UUID uuid) {
        return bussinesProjectRepository.findById(uuid);
    }


    @Transactional
    public BusinessProject save(BusinessProjectDTO request) throws Exception {
        Optional<ProfileClient> client = clientRepository.findById(UUID.fromString(request.getIdProfileClient()));
        if (client.isEmpty()) { throw new Exception("Perfil não encontrado");}

        BusinessProject project = new BusinessProject();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setPriceDay(request.getPriceDay());
        project.setPriceHour(request.getPriceHour());
        project.setClosingDate(request.getClosingDate());
        project.setStateBusiness(StateBusiness.Open);

        BusinessProject projectSave = bussinesProjectRepository.save(project);

        BusinessProjectProfile profile = new BusinessProjectProfile();
        profile.setBusinessProject(projectSave);
        profile.setProfileClient(client.get());
        projectSave.getProfiles().add(profile);

        return bussinesProjectRepository.save(project);

    }

    @Transactional
    public void delete(BusinessProject project) {
        bussinesProjectRepository.delete(project);
    }
}
