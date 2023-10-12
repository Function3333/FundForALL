package com.fundingForAll.www.repositoryTest;

import com.fundingForAll.www.content.Content;
import com.fundingForAll.www.content.ContentRepository;
import com.fundingForAll.www.content.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @Test
    @Transactional
    void findById() {
        Image image = new Image();
        String imageId = contentRepository.save(image);

        Content findContent = contentRepository.findById(imageId).get();
        Assertions.assertThat(image).isEqualTo(findContent);
    }
}
